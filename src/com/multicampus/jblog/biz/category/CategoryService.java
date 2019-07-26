package com.multicampus.jblog.biz.category;

import java.util.ArrayList;

public interface CategoryService {

	public void addCategory(CategoryVO vo);

	public ArrayList<CategoryVO> getCategoryList(CategoryVO vo);

	public void deleteCategory(CategoryVO vo);

}
