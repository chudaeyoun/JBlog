package com.multicampus.jblog.biz.blog;

import java.util.ArrayList;

public interface BlogService {
	public BlogVO getBlog(BlogVO vo);
	public void addBlog(BlogVO vo);
	public void updateBlog(BlogVO vo);
	public void requestBlogDelete(BlogVO vo);
	public void deleteBlog(BlogVO vo);
	public ArrayList<BlogVO> getBlogList(BlogVO vo);
}
