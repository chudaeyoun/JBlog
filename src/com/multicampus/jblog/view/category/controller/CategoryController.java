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
		// 세션으로부터 userId 정보 추출하여 blog_id에 저장한다.
		vo.setBlogId((String)session.getAttribute("userId"));
		
		// 카테고리 목록을 조회하여 model 객체에 저장한다.
		ArrayList<CategoryVO> categoryList = 
			categoryService.getCategoryList(vo);
		model.addAttribute("categoryList", categoryList);
		return "blogadmin_category.jsp";
	}
	
	@RequestMapping("/getCategory.do")
	public String getCategory(CategoryVO vo, HttpSession session, Model model){
		// 세션으로부터 userId 정보 추출하여 blog_id에 저장한다.
		vo.setBlogId((String)session.getAttribute("userId"));
		
		// 카테고리 목록을 조회하여 model 객체에 저장한다.
		ArrayList<CategoryVO> categoryList = categoryService.getCategoryList(vo);
		model.addAttribute("categoryList", categoryList);
		
		// 사용자가 선택한 특정 카테고리도 조회하여 model에 저장한다.
		model.addAttribute("category", categoryService.getCategory(vo));
		
		// 등록이 아닌 상세 화면이 보이도록 categoryFlag 값을 update로 변경한다.
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





