package com.multicampus.jblog.biz.category.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multicampus.jblog.biz.category.CategoryService;
import com.multicampus.jblog.biz.category.CategoryVO;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	
	public void addCategory(CategoryVO vo) {
		categoryDAO.addCategory(vo);
	}

	public ArrayList<CategoryVO> getCategoryList(CategoryVO vo) {
		return categoryDAO.getCategoryList(vo);
	}

	public void deleteCategory(CategoryVO vo) {
		categoryDAO.deleteCategory(vo);
	}

	public Object getCategory(CategoryVO vo) {
		return categoryDAO.getCategory(vo);
	}

	public void updateCategory(CategoryVO vo) {
		categoryDAO.updateCategory(vo);
	}

}













