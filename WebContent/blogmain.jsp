<%@page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.multicampus.jblog.biz.blog.BlogVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.multicampus.jblog.biz.category.CategoryVO"%>
<%@page import="com.multicampus.jblog.biz.post.PostVO"%>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JBlog ��α� ����</title>
	<Link rel="stylesheet" href="css/theme.css">
</head>

<%
	BlogVO blog = (BlogVO)session.getAttribute("blog");
	ArrayList<CategoryVO> categoryList = (ArrayList)request.getAttribute("categoryList");
	ArrayList<PostVO> postList = (ArrayList)request.getAttribute("postList");
%>

<body background="images/kubrickbgcolor.jpg">
<center>
	<table background="images/kubrickheader.jpg"
		 width="760" height="200" border="0" cellpadding="0" cellspacing="0">
      <tr>	<td height="60">&nbsp;</td></tr>
      <!-- ��α� ����� �±� ���� -->
      <tr>	<td height="60" class="blogtitle"><%= blog.getTitle() %></td></tr>
      <tr>	<td height="20" class="blogtag"><%= blog.getTag() %></td></tr>
      <!-- ��α� ����� �±� �� -->      
      <tr>	<td height="60">&nbsp;</td></tr>
    </table>
    <table background="images/kubrickbg.jpg" width="760" height="300" border="0" cellpadding="0" cellspacing="0">
      <tr><td height="10">&nbsp;</td></tr>
      <tr>
      	<td width="20">&nbsp;</td>
      	<td width="530" valign="top">
	      	<!-- ����Ʈ ���� -->
	      	<%for(PostVO post : postList){ %>
	      	<table height="10" border="0" cellpadding="0" cellspacing="0">
	      		<tr><td class="posttitle"><%= post.getTitle() %></td><td align="right"><a href="updatePostView.do?postId=<%= post.getPostId() %>">EDIT</a> / <a href="deletePost.do?postId=<%= post.getPostId() %>">DEL</a></td></tr>
	      		<tr><td class="postdate">2011/03/09</td></tr>
	      		<tr><td class="postcontent"><%= post.getContent() %></td></tr>
	      		<tr><td height="5">&nbsp;</td></tr>
	      		<tr><td class="postwriter">posted by <%= (String)session.getAttribute("userId") %> in J2EE, ���� 1</td></tr>
	      	</table>
	      	<br><hr><br>
	      	<%} %>
	      	<!-- ����Ʈ ��-->      	
      	</td>
      	<td width="20">&nbsp;</td>
      	<td width="190" valign="top">
      	<!-- �α���, ������ �޴�, �ΰ�, ī�װ� ���� -->
      	<table cellpadding="0" cellspacing="0">
      		<tr><td>
      		<% if(session.getAttribute("userId") == null){ %>
      		<a href="loginView.do">�α���</a>&nbsp;&nbsp;
      		<% }else{ %>
      		<a href="logout.do">�α׾ƿ�</a>&nbsp;&nbsp;
      		<% } %>
      		<% if(blog.getBlogId().equals(session.getAttribute("userId"))){ %>
      		<a href="blogadmin_basic.jsp">��α� ����</a></td></tr>
      		<% } %>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td><img height="80" src="images/j2eelogo.jpg" border="0"></td></tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td class="categorytitle">ī�װ�</td></tr>
      		<% for(CategoryVO category : categoryList){ %>
      		<tr><td class="categoryitem"><a class="title" href="blogMain.do?categoryId=<%= category.getCategoryId() %>"><%= category.getCategoryName() %></a></td></tr>
      		<% } %>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td align="center"><a href="index.jsp"><img width="80" src="images/logo.jpg" border="0"></a></td></tr>
      	</table>
      	<!-- �α���, ������ �޴�, �ΰ�, ī�װ� �� -->
      	</td>
      </tr>
    </table>
    <table background="images/kubrickfooter.jpg" width="760" height="63" border="0" cellpadding="0" cellspacing="0">
      <tr>
      	<td class="blogfooter">J2EE �̾߱� is powered by JBlog</td>
      </tr>
	</table>
</center>
   	
</body>
</html>