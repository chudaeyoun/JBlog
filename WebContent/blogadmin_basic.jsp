<%@page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.multicampus.jblog.biz.blog.BlogVO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JBlog �⺻���� ����</title>
	<Link rel="stylesheet" href="css/theme.css">
	<script type="text/javascript">
	function popup() {
		window.open('./blogadmin_request_remove.jsp', 'popup1', 'width=500,height=200,location=no,status=no,menubar=no,scrollbars=no,resizale=no,left=150,top=150');
	}
	</script>
</head>

<%
	// ���ǿ� ����� Blog ������ �����Ѵ�.
	BlogVO blog = (BlogVO)session.getAttribute("blog");
%>

<body background="images/kubrickbgcolor.jpg">
<center>
	<table background="images/kubrickheader.jpg"
		 width="760" height="200" border="0" cellpadding="0" cellspacing="0">
      <tr>	<td height="60">&nbsp;</td></tr>
      <!-- ���α� ����� �±� ���� -->
      <tr>	<td height="60" class="blogtitle"><%= blog.getTitle() %></td></tr>
      <tr>	<td height="20" class="blogtag"><%= blog.getTag() %></td></tr>
      <!-- ���α� ����� �±� �� -->      
      <tr>	<td align="right" height="60">
      <a href="logout.do">�α׾ƿ�</a>&nbsp;&nbsp;<a href="blogMain.do">�� ���α� ����</a>
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
      	<a class="title" href="getCategoryList.do"><b>ī�װ���</b></a>&nbsp;&nbsp;
      	<a class="title" href="addPostView.do"><b>���ۼ�</b></a>&nbsp;&nbsp;
      	<a class="title" href="javascript:popup();"><b>���α׻���</b></a>&nbsp;&nbsp;
      	<!-- �޴� �� -->           	
      	</td> 
      </tr>
      <tr><td height="5">&nbsp;</td></tr>
      <tr>
      	<td height="10">&nbsp;</td>
      	<td>
      	<!-- �۾� ȭ��  ���� -->  
      	<form action="updateBlog.do" method="post">      	
      	<table width="720"  border="0" cellpadding="0" cellspacing="0">
      		<tr>
      			<td width="150" class="inputlabel">���α� ���� :</td>
      			<td><input class="inputtext" type="text" size="40" name="title" value="<%= blog.getTitle() %>"></td>
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">���α� �±� :</td>
      			<td><input class="inputtext" type="text" size="50" name="tag" value="<%= blog.getTag() %>"></td>
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">���������� ����Ʈ  :</td>
      			<td><input class="inputtext" type="text" size="4" name="cntDisplayPost" value="<%= blog.getCntDisplayPost() %>"></td>      			
      		</tr>
      		<tr>
      			<td width="150" class="inputlabel">�ΰ��̹���  :</td>
      			<td>&nbsp;<img height="80" src="images/j2eelogo.jpg" border="0"></td>      			
      		</tr>      		
      		<tr>
      			<td width="150" class="inputlabel">&nbsp;</td>
      			<td><input class="inputtext" type="text" size="40" name="logoFile">
      			<input type="submit" value="ã�ƺ���"></td>      			
      		</tr>  
	        <tr>
	      	    <td height="40" colspan="10" align="center">
	      		<input type="submit" value="Ȯ��">
				</td>
	      </tr>               		
      	</table>
      	</form>   
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