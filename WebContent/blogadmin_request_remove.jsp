<%@ page contentType="text/html;charset=euc-kr" language="java"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.multicampus.jblog.biz.blog.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JBlog ��α� ������û</title>
	<Link rel="stylesheet" href="./css/theme.css">
	<script type="text/javascript">
	function goMain(){
		opener.location.href = './index.jsp';
		window.close();
	}
	</script>
</head>
<body>
	<center>
	<form method="post" action="requestBlogDelete.do">
   	<input type="hidden" name="blogID" value="<%= session.getAttribute("userId") %>"></input>
	<table width="400"  height="100" border="0" cellpadding="0" cellspacing="0">
      		<tr>
      			<td width="50" rowspan="2" align="center"><img src="images/worning.jpg" border="0"></td>
      			<td width="350" class="tablelabel">��α� ������ �����ڿ��� ��û�Ͻðڽ��ϱ� ?</td>
      		</tr>
      		<tr>
      			<td width="350" class="tablelabel">(�����Ͻø� ���� ��α״� ��Ȱ��ȭ �Ǹ� ������ Ȯ���� ��α׳��� ��� �����ʹ� �����˴ϴ�.)</td>
      		</tr>
      		<tr>
      			<td colspan="2" align="center" class="tablelabel">
      				<input type="submit" value="����" onclick="goMain();"/>
      				<input type="button" value="���" onclick="window.close();"/>
      			</td>
      		</tr>
    </table>
    </form>
    </center>
</body>
</html>


