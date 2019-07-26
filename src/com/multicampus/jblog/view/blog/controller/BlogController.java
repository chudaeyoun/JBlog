package com.multicampus.jblog.view.blog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multicampus.jblog.biz.blog.BlogService;
import com.multicampus.jblog.biz.blog.BlogVO;
import com.multicampus.jblog.biz.category.CategoryService;
import com.multicampus.jblog.biz.category.CategoryVO;
import com.multicampus.jblog.biz.post.PostService;
import com.multicampus.jblog.biz.post.PostVO;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@RequestMapping("/addBlog.do")
	public String addBlog(BlogVO vo, HttpSession session){
		// BlogService 이용해서 블로그를 생성한다.
		vo.setBlogId((String)session.getAttribute("userId"));
		vo.setCntDisplayPost(10);
		vo.setStatus("운영");
		vo.setTag("NO Tags");
		vo.setFileName("j2eelogo.jpg");		
		blogService.addBlog(vo);
		
		// 기본으로 '미분류'카테고리 생성
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setBlogId((String)session.getAttribute("userId"));
		categoryVO.setCategoryName("미분류");
		categoryVO.setCntDisplayPost(5);
		categoryVO.setDisplayType("타이틀");
		categoryVO.setDescription("기본 카테고리입니다...");
		categoryService.addCategory(categoryVO);
		
		// Blog가 생성되었음을 알려주기 위해서 세션에 생성된 블로그를 저장한다.
		session.setAttribute("blog", blogService.getBlog(vo));
		
		return "index.jsp";
	}
	
	@RequestMapping("/blogMain.do")
	public String blogMain(CategoryVO vo, HttpSession session, Model model){
		String blogId = null;
		// 블로그 검색 화면을 통해 블로그 메인 페이지를 요청한 경우
		if(vo.getBlogId() != null){
			blogId = vo.getBlogId();
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogId(blogId);
			BlogVO blog = blogService.getBlog(blogVO);
			session.setAttribute("blog", blog);
		} else {
			blogId = (String)session.getAttribute("userId");
		}
		
		// 해당 블로그에 등록된 CATEGORY 목록을 조회한다.
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setBlogId(blogId);
		ArrayList<CategoryVO> categoryList = categoryService.getCategoryList(categoryVO);
		model.addAttribute("categoryList", categoryList);
		
		// 해당 블로그에 등록된 POST 목록을 조회한다.
		PostVO postVO = new PostVO();
		postVO.setBlogId(blogId);
		if(vo.getCategoryId() == null)
			vo.setCategoryId("");
		postVO.setCategoryId("%"+vo.getCategoryId()+"%");
		ArrayList<PostVO> postList = postService.getPostList(postVO);
		model.addAttribute("postList", postList);
		
		return "blogmain.jsp";
	}
	
	@RequestMapping("/deleteBlog.do")
	public String deleteBlog(BlogVO vo, HttpSession session){
		// 사용자가 입력한 정보로 블로그 상태를 수정한다.
		blogService.deleteBlog(vo);
		
		//session.removeAttribute("blog");
		// 블로그 삭제 후에 블로그 검색 화면으로 이동한다.		
		return "redirect:/getBlogList.do";
	}
	
	@RequestMapping("/getBlogList.do")
	public String getBlogList(BlogVO vo, HttpSession session, Model model){
		if(vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		
		ArrayList<BlogVO> blogList = blogService.getBlogList(vo);
		model.addAttribute("blogList", blogList);
		return "index.jsp";
	}
	
	@RequestMapping("/requestBlogDelete.do")
	public String requestBlogDelete(BlogVO vo, HttpSession session){
		// 사용자가 입력한 정보로 블로그 상태를 수정한다.
		vo.setBlogId((String)session.getAttribute("userId"));
		blogService.requestBlogDelete(vo);

		// 블로그 상태 수정 후에 index 화면으로 이동한다.		
		return "index.jsp";
	}
	
	@RequestMapping("/updateBlog.do")
	public String updateBlog(BlogVO vo, HttpSession session){
		// 사용자가 입력한 정보로 블로그를 수정한다.
		vo.setBlogId((String)session.getAttribute("userId"));
		blogService.updateBlog(vo);
		
		// 수정된 블로그 정보를 세션에 저장한다.
		session.setAttribute("blog", vo);
		
		// 블로그 수정 후에 블로그 메인 화면으로 이동한다.		
		return "blogMain.do";
	}
}
