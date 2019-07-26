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
		// BlogService �̿��ؼ� ��α׸� �����Ѵ�.
		vo.setBlogId((String)session.getAttribute("userId"));
		vo.setCntDisplayPost(10);
		vo.setStatus("�");
		vo.setTag("NO Tags");
		vo.setFileName("j2eelogo.jpg");		
		blogService.addBlog(vo);
		
		// �⺻���� '�̺з�'ī�װ� ����
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setBlogId((String)session.getAttribute("userId"));
		categoryVO.setCategoryName("�̺з�");
		categoryVO.setCntDisplayPost(5);
		categoryVO.setDisplayType("Ÿ��Ʋ");
		categoryVO.setDescription("�⺻ ī�װ��Դϴ�...");
		categoryService.addCategory(categoryVO);
		
		// Blog�� �����Ǿ����� �˷��ֱ� ���ؼ� ���ǿ� ������ ��α׸� �����Ѵ�.
		session.setAttribute("blog", blogService.getBlog(vo));
		
		return "index.jsp";
	}
	
	@RequestMapping("/blogMain.do")
	public String blogMain(CategoryVO vo, HttpSession session, Model model){
		String blogId = null;
		// ��α� �˻� ȭ���� ���� ��α� ���� �������� ��û�� ���
		if(vo.getBlogId() != null){
			blogId = vo.getBlogId();
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogId(blogId);
			BlogVO blog = blogService.getBlog(blogVO);
			session.setAttribute("blog", blog);
		} else {
			blogId = (String)session.getAttribute("userId");
		}
		
		// �ش� ��α׿� ��ϵ� CATEGORY ����� ��ȸ�Ѵ�.
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setBlogId(blogId);
		ArrayList<CategoryVO> categoryList = categoryService.getCategoryList(categoryVO);
		model.addAttribute("categoryList", categoryList);
		
		// �ش� ��α׿� ��ϵ� POST ����� ��ȸ�Ѵ�.
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
		// ����ڰ� �Է��� ������ ��α� ���¸� �����Ѵ�.
		blogService.deleteBlog(vo);
		
		//session.removeAttribute("blog");
		// ��α� ���� �Ŀ� ��α� �˻� ȭ������ �̵��Ѵ�.		
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
		// ����ڰ� �Է��� ������ ��α� ���¸� �����Ѵ�.
		vo.setBlogId((String)session.getAttribute("userId"));
		blogService.requestBlogDelete(vo);

		// ��α� ���� ���� �Ŀ� index ȭ������ �̵��Ѵ�.		
		return "index.jsp";
	}
	
	@RequestMapping("/updateBlog.do")
	public String updateBlog(BlogVO vo, HttpSession session){
		// ����ڰ� �Է��� ������ ��α׸� �����Ѵ�.
		vo.setBlogId((String)session.getAttribute("userId"));
		blogService.updateBlog(vo);
		
		// ������ ��α� ������ ���ǿ� �����Ѵ�.
		session.setAttribute("blog", vo);
		
		// ��α� ���� �Ŀ� ��α� ���� ȭ������ �̵��Ѵ�.		
		return "blogMain.do";
	}
}
