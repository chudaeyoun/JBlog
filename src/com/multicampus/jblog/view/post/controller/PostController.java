package com.multicampus.jblog.view.post.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multicampus.jblog.biz.blog.BlogVO;
import com.multicampus.jblog.biz.category.CategoryService;
import com.multicampus.jblog.biz.category.CategoryVO;
import com.multicampus.jblog.biz.post.PostService;
import com.multicampus.jblog.biz.post.PostVO;

@Controller
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/addPostView.do")
	public String addPostView(HttpSession session, Model model){
		CategoryVO vo = new CategoryVO();
		vo.setBlogId((String)session.getAttribute("userId"));
		ArrayList<CategoryVO> categoryList = categoryService.getCategoryList(vo);
		model.addAttribute("categoryList", categoryList);
		return "blogadmin_write.jsp";
	}
	
	@RequestMapping("/addPost.do")
	public String addPost(PostVO vo){
		postService.addPost(vo);
		return "redirect:/blogMain.do";
	}
	
	@RequestMapping("/deletePost.do")
	public String deletePost(PostVO vo){
		postService.deletePost(vo);
		return "redirect:/blogMain.do";
	}
	
	@RequestMapping("/updatePostView.do")
	public String updatePostView(PostVO vo, HttpSession session, Model model){
		BlogVO blogVO = (BlogVO)session.getAttribute("blog");
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setBlogId(blogVO.getBlogId());
		ArrayList<CategoryVO> categoryList = categoryService.getCategoryList(categoryVO);
		model.addAttribute("categoryList", categoryList);
		
		PostVO post = postService.getPost(vo);
		model.addAttribute("post", post);		
		return "blogadmin_write.jsp";
	}
	
	@RequestMapping("/updatePost.do")
	public String updatePost(PostVO vo){
		postService.updatePost(vo);
		return "redirect:/blogMain.do";
	}
}
