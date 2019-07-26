package com.multicampus.jblog.biz.category;

import java.sql.Date;

public class CategoryVO {
	private String blogId;
	private String categoryId;
	private String categoryName;
	private String displayType;
	private int cntDisplayPost;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
	
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public int getCntDisplayPost() {
		return cntDisplayPost;
	}
	public void setCntDisplayPost(int cntDisplayPost) {
		this.cntDisplayPost = cntDisplayPost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "CategoryVO [blogId=" + blogId + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", cntDisplayPost="
				+ cntDisplayPost + ", createdDate=" + createdDate
				+ ", description=" + description + ", displayType="
				+ displayType + ", modifiedDate=" + modifiedDate + "]";
	}
}
