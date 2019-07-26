package com.multicampus.jblog.biz.post.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multicampus.jblog.biz.post.PostService;
import com.multicampus.jblog.biz.post.PostVO;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(PostVO vo){
		postDAO.addPost(vo);
	}
	
	public ArrayList<PostVO> getPostList(PostVO vo){
		return postDAO.getPostList(vo);
	}
	
	public void deletePost(PostVO vo) {
		postDAO.deletePost(vo);
	}

	public PostVO getPost(PostVO vo) {
		return postDAO.getPost(vo);
	}

	public void updatePost(PostVO vo) {
		postDAO.updatePost(vo);
	}
}
