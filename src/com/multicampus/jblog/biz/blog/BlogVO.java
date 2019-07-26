package com.multicampus.jblog.biz.blog;

public class BlogVO {
	private String blogId;
	private String title;
	private String tag;
	private int cntDisplayPost;
	private String status;
	private String fileName;
	private String userName;	
	private String searchCondition;
	private String searchKeyword;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCntDisplayPost() {
		return cntDisplayPost;
	}
	public void setCntDisplayPost(int cntDisplayPost) {
		this.cntDisplayPost = cntDisplayPost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "BlogVO [blogId=" + blogId + ", cntDisplayPost="
				+ cntDisplayPost + ", fileName=" + fileName
				+ ", searchCondition=" + searchCondition + ", searchKeyword="
				+ searchKeyword + ", status=" + status + ", tag=" + tag
				+ ", title=" + title + ", userName=" + userName + "]";
	}

}
