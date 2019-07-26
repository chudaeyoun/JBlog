package com.multicampus.jblog.biz.blog.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multicampus.jblog.biz.blog.BlogService;
import com.multicampus.jblog.biz.blog.BlogVO;

@Component
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDAO blogDAO;
	
	public BlogVO getBlog(BlogVO vo) {
		return blogDAO.getBlog(vo);
	}

	public void addBlog(BlogVO vo) {
		blogDAO.addBlog(vo);
	}
	
	public void updateBlog(BlogVO vo) {
		blogDAO.updateBlog(vo);
	}
	
	public void requestBlogDelete(BlogVO vo){
		blogDAO.requestBlogDelete(vo);
	}
	
	public void deleteBlog(BlogVO vo){
		blogDAO.deleteBlog(vo);
	}

	public ArrayList<BlogVO> getBlogList(BlogVO vo) {
		return blogDAO.getBlogList(vo);
	}
}
