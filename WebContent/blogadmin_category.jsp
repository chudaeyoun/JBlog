<%@page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.multicampus.jblog.biz.blog.BlogVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.multicampus.jblog.biz.category.CategoryVO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JBlog 카테고리 관리</title>
	<Link rel="stylesheet" href="css/theme.css">
	<script type="text/javascript">
	function popup() {
		window.open('./blogadmin_request_remove.jsp', 'popup1', 'width=500,height=200,location=no,status=no,menubar=no,scrollbars=no,resizale=no,left=150,top=150');
	}
	</script>
</head>

<%
	// 블로그의 title, tag 정보를 출력하기 위해서 세션으로 부터 blog를 추출한다.
	BlogVO blog = (BlogVO)session.getAttribute("blog");
	// Model에 저장된 카테고리 목록을 추출한다.
	ArrayList<CategoryVO> categoryList = 
		(ArrayList)request.getAttribute("categoryList");
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
      	<b>카테고리</b>&nbsp;&nbsp;
      	<a class="title" href="addPostView.do"><b>글작성</b></a>&nbsp;&nbsp;
      	<a class="title" href="javascript:popup();"><b>블로그삭제</b></a>&nbsp;&nbsp;
      	<!-- 메뉴 끝 -->           	 
      	</td>
      </tr>
      <tr><td height="5">&nbsp;</td></tr>
      <tr>
      	<td height="10">&nbsp;</td>
      	<td>
      	<!-- 작업 화면  시작 -->         	     	
      	<table width="720"  border="1" cellpadding="0" cellspacing="0">
      		<tr>
      			<td width="50" class="tablelabel">번호</td>
      			<td width="150" class="tablelabel">카테고리명</td>
      			<td width="80" class="tablelabel">보이기 유형</td>
      			<td width="70" class="tablelabel">포스트 수</td>
      			<td width="280" class="tablelabel">설명</td>
      			<td width="70" class="tablelabel">삭제</td>      			
      		</tr>
      		<% 	
      			int num = 0;
      			for(CategoryVO category : categoryList){ %>
			<tr>
				<td class="tablecontent" align="center"><%= ++num %></td>
				<td class="tablecontent"><a href="getCategory.do?categoryId=<%= category.getCategoryId() %>"><%= category.getCategoryName() %></a></td>
				<td class="tablecontent" align="center"><%= category.getDisplayType() %></td>
				<td class="tablecontent" align="center"><%= category.getCntDisplayPost() %></td>
				<td class="tablecontent"><%= category.getDescription() %></td>
				<td class="tablecontent" align="center">&nbsp;<a href="deleteCategory.do?categoryId=<%= category.getCategoryId() %>"><img height="9" src="images/delete.jpg" border="0"></a></td>
			</tr>  
 			<%	} %>		    					
      	</table>
      	
      	<%
      		String categoryFlag = (String)session.getAttribute("categoryFlag");
    		if(categoryFlag != null && categoryFlag.equals("update")){ 
    			CategoryVO category = (CategoryVO)request.getAttribute("category");
	   	%>
      	<form action="updateCategory.do" method="post">   
      	<input type="hidden" name="categoryId" value="<%= category.getCategoryId() %>"/>
      	<table width="720"  border="0" cellpadding="0" cellspacing="0">
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td class="tdcontent" height="5"><b>카테고리 수정</b></td></tr>
      		<tr><td height="5">&nbsp;</td></tr>      		
      		<tr>
      			<td width="150" class="inputlabel">카테고리명 :</td>
      			<td><input class="inputtext" type="text" size="40" name="categoryName" value="<%= category.getCategoryName() %>"></td>
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">보이기 유형 :</td>
      			<td class="tdcontent">
      				<input type="radio" name="displayType" value="타이틀" checked>타이틀&nbsp;&nbsp;
      				<input type="radio" name="displayType" value="텍스트">텍스트&nbsp;&nbsp;</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">포스트 수 :</td>
      			<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost" value="<%= category.getCntDisplayPost() %>">개(1~20)</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">설명 :</td>
      			<td><input class="inputtext" type="text" size="50" name="description" value="<%= category.getDescription() %>"></td>
      		</tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr>
      			<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 정보 수정"></td>
      		</tr>      		      		
      	</table>    
      	</form> 
      	<%	} else { %>
      	<form action="addCategory.do" method="post">   
      	<table width="720"  border="0" cellpadding="0" cellspacing="0">
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td class="tdcontent" height="5"><b>새로운 카테고리 추가</b></td></tr>
      		<tr><td height="5">&nbsp;</td></tr>      		
      		<tr>
      			<td width="150" class="inputlabel">카테고리명 :</td>
      			<td><input class="inputtext" type="text" size="40" name="categoryName"></td>
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">보이기 유형 :</td>
      			<td class="tdcontent">
      				<input type="radio" name="displayType" value="타이틀" checked>타이틀&nbsp;&nbsp;
      				<input type="radio" name="displayType" value="텍스트">텍스트&nbsp;&nbsp;</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">포스트 수 :</td>
      			<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost">개(1~20)</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">설명 :</td>
      			<td><input class="inputtext" type="text" size="50" name="description"></td>
      		</tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr>
      			<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 추가"></td>
      		</tr>      		      		
      	</table>    
      	</form> 
      	<%	}  %>	      	
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