package com.multicampus.jblog.view.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multicampus.jblog.biz.blog.BlogService;
import com.multicampus.jblog.biz.blog.BlogVO;
import com.multicampus.jblog.biz.user.UserService;
import com.multicampus.jblog.biz.user.UserVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/loginView.do")
	public String loginView(){
		return "login.jsp";
	}
	
	@RequestMapping("/login.do")
	public String login(UserVO vo, HttpSession session){
		UserVO user = userService.getUser(vo);
		if(user != null){
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userRole", user.getRole());
			BlogVO blog = new BlogVO();
			blog.setBlogId(user.getUserId());
			blog = blogService.getBlog(blog);
			if(blog != null){
				session.setAttribute("blog", blog);
			}
		}
		return "index.jsp";
	}
}
