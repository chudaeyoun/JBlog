package com.multicampus.jblog.biz.post;

import java.util.ArrayList;

public interface PostService {
	public void addPost(PostVO vo);
	
	public ArrayList<PostVO> getPostList(PostVO vo);
	
	public void deletePost(PostVO vo);
	
	public PostVO getPost(PostVO vo);
	
	public void updatePost(PostVO vo);
}
