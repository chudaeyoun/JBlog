<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JBlog</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
	<center>
		<form action="getBlogList.do" method="post">
			<table width="100%" height=320 border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height=40 colspan="10">&nbsp;</td>
				</tr>
				<tr>
					<td width="100%" height="120" colspan="10" align="center"><img
						src="images/logo.jpg" border="0"></td>
				</tr>
				<tr>
					<td width="30%" height="30">&nbsp;</td>
					<td width="70%" colspan="2"><c:if test="${userId == null }">
							<a href="loginView.do"><b>�α���</b></a>&nbsp;&nbsp;
      						</c:if> <c:if test="${userId != null }">
							<c:if test="${blog == null }">
								<a href="blogcreate.jsp"><b>��α׵��</b></a>&nbsp;&nbsp;
	      					</c:if>
							<c:if test="${blog != null }">
								<a href="blogMain.do"><b>�� ��α׷� ����</b></a>
							</c:if>
						</c:if></td>
				</tr>
				<tr>
					<td width="30%" height="20">&nbsp;</td>
					<td width="40%"><input type="text" name="searchKeyword"
						size="50"></td>
					<td width="30%">&nbsp;<input type="submit" value="�˻�"></td>
				</tr>
				<tr>
					<td height="20" colspan="10" align="center" class="tdcontent">
						<input type="radio" name="searchCondition" value="TITLE"
						checked="checked">��α� ����&nbsp;&nbsp; <input type="radio"
						name="searchCondition" value="TAG">�±�&nbsp;&nbsp; <input
						type="radio" name="searchCondition" value="BLOGGER">��ΰ�
					</td>
				</tr>
			</table>
		</form>

		<c:if test="${blogList != null }">
			<table width="720" height="100" border="1" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="320" class="tablelabel">��α� ����</td>
					<td width="100" class="tablelabel">��ΰ�</td>
					<td width="100" class="tablelabel">�ΰ�</td>
					<td width="100" class="tablelabel">����</td>
					<td width="100" class="tablelabel">����</td>
				</tr>
				<c:forEach items="${blogList }" var="blog">
					<tr>
						<td class="tablecontent"><a
							href="blogMain.do?blogId=${blog.blogId }">${blog.title }</a></td>
						<td class="tablecontent" align="center">${blog.userName }</td>
						<td class="tablecontent" align="center"><img height="32"
							src="images/j2eelogo.jpg" border="0"></td>
						<td class="tablecontent" align="center">${blog.status }</td>
						<c:if test="${blog.status == '������û' }">
							<td class="tablecontent" align="center"><a
								href="deleteBlog.do?blogId=${blog.blogId }"> <img height="9"
									src="images/delete.jpg" border="0">
							</a></td>
						</c:if>
						<c:if test="${blog.status != '������û' }">
							<td class="tablecontent" align="center">&nbsp;</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</center>
</body>
</html>