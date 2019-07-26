<%@page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.multicampus.jblog.biz.blog.BlogVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.multicampus.jblog.biz.category.CategoryVO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JBlog ī�װ� ����</title>
	<Link rel="stylesheet" href="css/theme.css">
	<script type="text/javascript">
	function popup() {
		window.open('./blogadmin_request_remove.jsp', 'popup1', 'width=500,height=200,location=no,status=no,menubar=no,scrollbars=no,resizale=no,left=150,top=150');
	}
	</script>
</head>

<%
	// ��α��� title, tag ������ ����ϱ� ���ؼ� �������� ���� blog�� �����Ѵ�.
	BlogVO blog = (BlogVO)session.getAttribute("blog");
	// Model�� ����� ī�װ� ����� �����Ѵ�.
	ArrayList<CategoryVO> categoryList = 
		(ArrayList)request.getAttribute("categoryList");
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
      <tr>	<td align="right" height="60">
      <a href="logout.do">�α׾ƿ�</a>&nbsp;&nbsp;<a href="blogMain.do">�� ��α� ����</a>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td></tr>
    </table>
    <table background="images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
      <tr><td height="10" colspan="10">&nbsp;</td></tr>
      <tr>
      	<td height="10" width="20">&nbsp;</td>
      	<td width="530" valign="top" class="tdcontent">
      	<!-- �޴� ���� -->     
      	<a class="title" href="blogadmin_basic.jsp"><b>�⺻����</b></a>&nbsp;&nbsp;
      	<b>ī�װ�</b>&nbsp;&nbsp;
      	<a class="title" href="addPostView.do"><b>���ۼ�</b></a>&nbsp;&nbsp;
      	<a class="title" href="javascript:popup();"><b>��α׻���</b></a>&nbsp;&nbsp;
      	<!-- �޴� �� -->           	 
      	</td>
      </tr>
      <tr><td height="5">&nbsp;</td></tr>
      <tr>
      	<td height="10">&nbsp;</td>
      	<td>
      	<!-- �۾� ȭ��  ���� -->         	     	
      	<table width="720"  border="1" cellpadding="0" cellspacing="0">
      		<tr>
      			<td width="50" class="tablelabel">��ȣ</td>
      			<td width="150" class="tablelabel">ī�װ���</td>
      			<td width="80" class="tablelabel">���̱� ����</td>
      			<td width="70" class="tablelabel">����Ʈ ��</td>
      			<td width="280" class="tablelabel">����</td>
      			<td width="70" class="tablelabel">����</td>      			
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
      		<tr><td class="tdcontent" height="5"><b>ī�װ� ����</b></td></tr>
      		<tr><td height="5">&nbsp;</td></tr>      		
      		<tr>
      			<td width="150" class="inputlabel">ī�װ��� :</td>
      			<td><input class="inputtext" type="text" size="40" name="categoryName" value="<%= category.getCategoryName() %>"></td>
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">���̱� ���� :</td>
      			<td class="tdcontent">
      				<input type="radio" name="displayType" value="Ÿ��Ʋ" checked>Ÿ��Ʋ&nbsp;&nbsp;
      				<input type="radio" name="displayType" value="�ؽ�Ʈ">�ؽ�Ʈ&nbsp;&nbsp;</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">����Ʈ �� :</td>
      			<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost" value="<%= category.getCntDisplayPost() %>">��(1~20)</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">���� :</td>
      			<td><input class="inputtext" type="text" size="50" name="description" value="<%= category.getDescription() %>"></td>
      		</tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr>
      			<td colspan="10" align="center">&nbsp;<input type="submit" value="ī�װ� ���� ����"></td>
      		</tr>      		      		
      	</table>    
      	</form> 
      	<%	} else { %>
      	<form action="addCategory.do" method="post">   
      	<table width="720"  border="0" cellpadding="0" cellspacing="0">
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr><td class="tdcontent" height="5"><b>���ο� ī�װ� �߰�</b></td></tr>
      		<tr><td height="5">&nbsp;</td></tr>      		
      		<tr>
      			<td width="150" class="inputlabel">ī�װ��� :</td>
      			<td><input class="inputtext" type="text" size="40" name="categoryName"></td>
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">���̱� ���� :</td>
      			<td class="tdcontent">
      				<input type="radio" name="displayType" value="Ÿ��Ʋ" checked>Ÿ��Ʋ&nbsp;&nbsp;
      				<input type="radio" name="displayType" value="�ؽ�Ʈ">�ؽ�Ʈ&nbsp;&nbsp;</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">����Ʈ �� :</td>
      			<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost">��(1~20)</td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">���� :</td>
      			<td><input class="inputtext" type="text" size="50" name="description"></td>
      		</tr>
      		<tr><td height="5">&nbsp;</td></tr>
      		<tr>
      			<td colspan="10" align="center">&nbsp;<input type="submit" value="ī�װ� �߰�"></td>
      		</tr>      		      		
      	</table>    
      	</form> 
      	<%	}  %>	      	
      	<!-- �۾� ȭ��  �� -->           	      	
      	</td>
      </tr>
      <tr>
      	<td height="10" colspan="10">&nbsp;</td>
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