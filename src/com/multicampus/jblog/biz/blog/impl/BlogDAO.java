package com.multicampus.jblog.biz.blog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.multicampus.jblog.biz.blog.BlogVO;
import com.multicampus.jblog.biz.common.JDBCUtil;

@Repository
public class BlogDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// SQL 명령어들
	private String BLOG_ADD = "insert into blog(blog_id, title, cnt_display_post, status, file_name, tag) values(?,?,?,?,?,?)";
	private String BLOG_GET = "select * from blog where blog_id=?";
	private String BLOG_UPDATE = "update blog set title=?, tag=?, cnt_display_post=? where blog_id=?";
	private String BLOG_DELETE_REQUEST = "update blog set status='삭제요청' where blog_id=?";
	private String BLOG_DELETE = "delete blog where blog_id=?";
	private String BLOG_LIST_TITLE = "select b.blog_id, b.cnt_display_post, b.status, b.file_name, b.title, b.tag, u.user_name from blog b, blog_user u where u.user_id=b.blog_id and b.title like ? order by b.title";
	private String BLOG_LIST_TAG = "select b.blog_id, b.cnt_display_post, b.status, b.file_name, b.title, b.tag, u.user_name from blog b, blog_user u where u.user_id=b.blog_id and b.tag like ? order by b.title";
	private String BLOG_LIST_BLOGGER = "select b.blog_id, b.cnt_display_post, b.status, b.file_name, b.title, b.tag, u.user_name from blog b, blog_user u where u.user_id=b.blog_id and u.user_name like ? order by b.title";

	public void addBlog(BlogVO vo){
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_ADD);
			stmt.setString(1, vo.getBlogId());
			stmt.setString(2, vo.getTitle());
			stmt.setInt(3, vo.getCntDisplayPost());
			stmt.setString(4, vo.getStatus());
			stmt.setString(5, vo.getFileName());
			stmt.setString(6, vo.getTag());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public BlogVO getBlog(BlogVO vo) {
		BlogVO blog = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_GET);	
			stmt.setString(1, vo.getBlogId());
			rs = stmt.executeQuery();
			if(rs.next()){
				blog = new BlogVO();
				blog.setBlogId(rs.getString("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blog.setFileName(rs.getString("FILE_NAME"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setTag(rs.getString("TAG"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blog;
	}

	public void updateBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getTag());
			stmt.setInt(3, vo.getCntDisplayPost());
			stmt.setString(4, vo.getBlogId());			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void requestBlogDelete(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_DELETE_REQUEST);
			stmt.setString(1, vo.getBlogId());			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void deleteBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_DELETE);
			stmt.setString(1, vo.getBlogId());			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public ArrayList<BlogVO> getBlogList(BlogVO vo) {
		ArrayList<BlogVO> blogList = new ArrayList<BlogVO>();
		try {
			conn = JDBCUtil.getConnection();
			if(vo.getSearchCondition().equals("TITLE")){
				stmt = conn.prepareStatement(BLOG_LIST_TITLE);
			} else if(vo.getSearchCondition().equals("TAG")){
				stmt = conn.prepareStatement(BLOG_LIST_TAG);
			} else if(vo.getSearchCondition().equals("BLOGGER")){
				stmt = conn.prepareStatement(BLOG_LIST_BLOGGER);
			}
			stmt.setString(1, "%"+vo.getSearchKeyword()+"%");
			rs = stmt.executeQuery();
			while(rs.next()){
				BlogVO blog = new BlogVO();
				blog.setBlogId(rs.getString("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blog.setFileName(rs.getString("FILE_NAME"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setTag(rs.getString("TAG"));
				blog.setUserName(rs.getString("USER_NAME"));
				blogList.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blogList;
	}
}











