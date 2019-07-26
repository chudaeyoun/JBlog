<%@page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.multicampus.jblog.biz.blog.BlogVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.multicampus.jblog.biz.category.CategoryVO"%>
<%@page import="com.multicampus.jblog.biz.post.PostVO"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JBlog 글 작성</title>
	<Link rel="stylesheet" href="css/theme.css">
	<script type="text/javascript">
	function popup() {
		window.open('./blogadmin_request_remove.jsp', 'popup1', 'width=500,height=200,location=no,status=no,menubar=no,scrollbars=no,resizale=no,left=150,top=150');
	}
	</script>
</head>

<%
	// 세션에 저장된 Blog 정보를 추출한다.
	BlogVO blog = (BlogVO)session.getAttribute("blog");

	// Model 에서 카테고리 목록을 추출한다. 
	ArrayList<CategoryVO> categoryList = 
		(ArrayList)request.getAttribute("categoryList");
	
	// Model 수정할 포스트 정보를 추출한다. 
	PostVO post = (PostVO)request.getAttribute("post");
%>

<body background="images/kubrickbgcolor.jpg">
<center>
	<table background="images/kubrickheader.jpg"
		 width="760" height="200" border="0" cellpadding="0" cellspacing="0">
      <tr>	<td height="60">&nbsp;</td></tr>
      <!-- 블로그 제목과 태그 시작 -->
       <tr>	<td height="60" class="blogtitle"><%= blog.getTitle() %></td></tr>
      <tr>	<td height="20" class="blogtag"><%= blog.getTag() %></td></tr>
      <!-- 블로그 제목과 태그 끝 -->      
      <tr>	<td align="right" height="60">
      <a href="logout.do">로그아웃</a>&nbsp;&nbsp;<a href="blogMain.do">내 블로그 메인</a>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td></tr>
    </table>
    <table background="images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
      <tr><td height="10" colspan="10">&nbsp;</td></tr>
      <tr>
      	<td height="10" width="20">&nbsp;</td>
      	<td width="530" valign="top" class="tdcontent">
      	<!-- 메뉴 시작 -->     
      	<a class="title" href="blogadmin_basic.jsp"><b>기본설정</b></a>&nbsp;&nbsp; 
      	<a class="title" href="getCategoryList.do"><b>카테고리</b></a>&nbsp;&nbsp;
      	<b>글작성</b>&nbsp;&nbsp;
      	<a class="title" href="javascript:popup();"><b>블로그삭제</b></a>&nbsp;&nbsp;
      	<!-- 메뉴 끝 -->           	
      	</td>
      </tr>
      <tr><td height="5">&nbsp;</td></tr>
      <tr>
      	<td height="10">&nbsp;</td>
      	<td>
      	<!-- 작업 화면  시작 -->  
      	<%	if(post != null){ %>
      	<form action="updatePost.do" method="post">  
      	<input type="hidden" name="postId" value="<%= post.getPostId() %>"/>      	
      	<table width="720"  border="0" cellpadding="0" cellspacing="0">
      		<tr>
      			<td width="50" class="inputlabel">제목 :</td>
      			<td width="390"><input class="inputtext" type="text" size="60" name="title" value="<%= post.getTitle() %>"></td>
      			<td width="300">
      			<select name="categoryId" class="inputtextarea">
      				<%for(CategoryVO category : categoryList){ %>
	      				<%if(category.getCategoryId().equals(post.getCategoryId())){ %>
	      				<option value="<%=category.getCategoryId() %>" selected><%= category.getCategoryName() %></option>
	      				<%}else{ %>
	      				<option value="<%=category.getCategoryId() %>"><%= category.getCategoryName() %></option>
	      				<%} %>
      				<%} %>
      			</select></td>
      		</tr>
      		<tr>
      			<td width="50" class="inputlabel">내용 :</td>
      			<td colspan="10"><textarea name="content" class="inputtextarea" rows="10" cols="100"><%= post.getContent() %></textarea></td>
      		</tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr>
      			<td colspan="10" align="center">&nbsp;<input type="submit" value="수정하기"></td>
      		</tr>
      	</table>
      	</form> 
		<%	} else { %>  
      	<form action="addPost.do" method="post">       	
      	<table width="720"  border="0" cellpadding="0" cellspacing="0">
      		<tr>
      			<td width="50" class="inputlabel">제목 :</td>
      			<td width="390"><input class="inputtext" type="text" size="60" name="title"></td>
      			<td width="300">
      			<select class="inputtextarea" name="categoryId">
      				<%for(CategoryVO category : categoryList){ %>
	      				<option value="<%=category.getCategoryId() %>"><%= category.getCategoryName() %></option>
      				<%} %>
      			</select></td>
      		</tr>
      		<tr>
      			<td width="50" class="inputlabel">내용 :</td>
      			<td colspan="10"><textarea name="content" class="inputtextarea" rows="10" cols="100"></textarea></td>
      		</tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr>
      			<td colspan="10" align="center">&nbsp;<input type="submit" value="확인"></td>
      		</tr>
      	</table>
      	</form>
      	<%	} %> 
      	<!-- 작업 화면  끝 -->           	      	
      	</td>
      </tr>
      <tr>
      	<td height="10" colspan="10">&nbsp;</td>
      </tr>
    </table>
    <table background="images/kubrickfooter.jpg" width="760" height="63" border="0" cellpadding="0" cellspacing="0">
      <tr>
      	<td class="blogfooter">J2EE 이야기 is powered by JBlog</td>
      </tr>
	</table>
</center>
   	
</body>
</html>