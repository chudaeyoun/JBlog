package com.multicampus.jblog.biz.category.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.multicampus.jblog.biz.category.CategoryVO;
import com.multicampus.jblog.biz.common.JDBCUtil;

@Repository
public class CategoryDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// SQL 명령어들
	private String CATEGORY_ADD = "insert into category(blog_id, category_id, category_name, display_type, description, cnt_display_post, created_date, modified_date) values(?,(select nvl(max(category_id), 0) + 1 from category),?,?,?,?,sysdate,sysdate)";
	private String CATEGORY_LIST = "select * from category where blog_id=? order by category_name desc";
	private String CATEGORY_GET = "select * from category where category_id=?";
	private String CATEGORY_DELETE = "delete category where category_id=?";
	
	public void addCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_ADD);
			stmt.setString(1, vo.getBlogId());
			stmt.setString(2, vo.getCategoryName());
			stmt.setString(3, vo.getDisplayType());
			stmt.setString(4, vo.getDescription());
			stmt.setInt(5, vo.getCntDisplayPost());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
	}

	public ArrayList<CategoryVO> getCategoryList(CategoryVO vo) {
		ArrayList<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_LIST);
			stmt.setString(1, vo.getBlogId());
			rs = stmt.executeQuery();
			while(rs.next()){
				CategoryVO category = new CategoryVO();
				category.setBlogId(rs.getString("BLOG_ID"));
				category.setCategoryId(rs.getString("CATEGORY_ID"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));
				category.setDisplayType(rs.getString("DISPLAY_TYPE"));
				category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
				category.setCreatedDate(rs.getDate("CREATED_DATE"));
				category.setModifiedDate(rs.getDate("MODIFIED_DATE"));
				categoryList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);			
		}
		return categoryList;
	}

	public void deleteCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_DELETE);
			stmt.setString(1, vo.getCategoryId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
	}

	public Object getCategory(CategoryVO vo) {
		CategoryVO category = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_GET);
			stmt.setString(1, vo.getCategoryId());
			rs = stmt.executeQuery();
			if(rs.next()){
				category = new CategoryVO();
				category.setBlogId(rs.getString("BLOG_ID"));
				category.setCategoryId(rs.getString("CATEGORY_ID"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));
				category.setDisplayType(rs.getString("DISPLAY_TYPE"));
				category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
				category.setCreatedDate(rs.getDate("CREATED_DATE"));
				category.setModifiedDate(rs.getDate("MODIFIED_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);			
		}
		return category;
	}

}
