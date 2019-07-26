package com.multicampus.jblog.biz.post.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.multicampus.jblog.biz.common.JDBCUtil;
import com.multicampus.jblog.biz.post.PostVO;

@Repository
public class PostDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// SQL 명령어들
	private String POST_ADD = "insert into post(post_id, category_id, title, content) values((select nvl(max(post_id), 0) + 1 from post),?,?,?)";
	private String POST_LIST = "select p.post_id, p.category_id, p.title, p.content from category c, post p where c.category_id = p.category_id and p.category_id like ? and c.blog_id = ?";
	private String POST_GET = "select * from post where post_id=?";
	private String POST_DELETE = "delete post where post_id=?";
	private String POST_UPDATE = "update post set title=?, content=?, category_id=? where post_id=?";
	
	public void addPost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_ADD);
			stmt.setString(1, vo.getCategoryId());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
	}
	
	public ArrayList<PostVO> getPostList(PostVO vo) {
		ArrayList<PostVO> postList = new ArrayList<PostVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_LIST);
			stmt.setString(1, vo.getCategoryId());
			stmt.setString(2, vo.getBlogId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				PostVO post = new PostVO();
				post.setPostId(rs.getString("POST_ID"));
				post.setCategoryId(rs.getString("CATEGORY_ID"));
				post.setTitle(rs.getString("TITLE"));
				post.setContent(rs.getString("CONTENT"));
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return postList;
	}
	
	public void deletePost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_DELETE);
			stmt.setString(1, vo.getPostId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
	}

	public PostVO getPost(PostVO vo) {
		PostVO post = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_GET);
			stmt.setString(1, vo.getPostId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				post = new PostVO();
				post.setPostId(rs.getString("POST_ID"));
				post.setCategoryId(rs.getString("CATEGORY_ID"));
				post.setTitle(rs.getString("TITLE"));
				post.setContent(rs.getString("CONTENT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return post;
	}

	public void updatePost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setString(3, vo.getCategoryId());
			stmt.setString(4, vo.getPostId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
