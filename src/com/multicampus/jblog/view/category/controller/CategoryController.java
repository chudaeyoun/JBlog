package com.multicampus.jblog.view.category.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multicampus.jblog.biz.category.CategoryService;
import com.multicampus.jblog.biz.category.CategoryVO;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/addCategory.do")
	public String addCategory(CategoryVO vo, HttpSession session){
		vo.setBlogId((String)session.getAttribute("userId"));
		categoryService.addCategory(vo);
		return "getCategoryList.do";
	}
		
	
	@RequestMapping("/getCategoryList.do")
	public String getCategoryList(CategoryVO vo, 
			                      HttpSession session, Model model){
		// �������κ��� userId ���� �����Ͽ� blog_id�� �����Ѵ�.
		vo.setBlogId((String)session.getAttribute("userId"));
		
		// ī�װ� ����� ��ȸ�Ͽ� model ��ü�� �����Ѵ�.
		ArrayList<CategoryVO> categoryList = 
			categoryService.getCategoryList(vo);
		model.addAttribute("categoryList", categoryList);
		return "blogadmin_category.jsp";
	}
	
	@RequestMapping("/getCategory.do")
	public String getCategory(CategoryVO vo, HttpSession session, Model model){
		// �������κ��� userId ���� �����Ͽ� blog_id�� �����Ѵ�.
		vo.setBlogId((String)session.getAttribute("userId"));
		
		// ī�װ� ����� ��ȸ�Ͽ� model ��ü�� �����Ѵ�.
		ArrayList<CategoryVO> categoryList = categoryService.getCategoryList(vo);
		model.addAttribute("categoryList", categoryList);
		
		// ����ڰ� ������ Ư�� ī�װ��� ��ȸ�Ͽ� model�� �����Ѵ�.
		model.addAttribute("category", categoryService.getCategory(vo));
		
		// ����� �ƴ� �� ȭ���� ���̵��� categoryFlag ���� update�� �����Ѵ�.
		session.setAttribute("categoryFlag", "update");
		return "blogadmin_category.jsp";
	}
	
	@RequestMapping("/deleteCategory.do")
	public String deleteCategory(CategoryVO vo, HttpSession session) {
		categoryService.deleteCategory(vo);
		session.removeAttribute("categoryFlag");
		return "getCategoryList.do";
	}
	
	@RequestMapping("/updateCategory.do")
	public String updateCategory(CategoryVO vo, HttpSession session) {
		categoryService.updateCategory(vo);
		session.removeAttribute("categoryFlag");
		return "getCategoryList.do";
	}

}





