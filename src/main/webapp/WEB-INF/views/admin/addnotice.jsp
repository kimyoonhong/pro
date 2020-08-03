<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<%
  request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
function backToList(obj){
    obj.action="${contextPath}/project/listprojects.do";
    obj.submit();
  }
</script>

</head>
<body>
<form name="addnotice" method="post"   action="${contextPath}/admin/addnotice.do"   enctype="multipart/form-data">
 <table border="0" align="center">
<tr>
<td    bgcolor="lightgreen">제목</td>
<td align="center"><input type="text" name="NOTICE_TITLE"  ></td>
</tr>
<tr>
<td    bgcolor="lightgreen">작성자</td>
<td align="center"><input type="text" name="ADMIN_NAME" value="${admin.ADMIN_NAME}" readonly></td>
<input type="hidden" name="ADMIN_ID" value="${admin.ADMIN_ID}">
</tr>
 <tr>
	<td  bgcolor="lightgreen" align="right" valign="top"><br>공지 사항 내용: </td>
	<td colspan=2><textarea name="NOTICE_CONTENT" rows="10" cols="65" maxlength="4000"></textarea> </td>
</tr>
 <tr>
	<td  align="center"   bgcolor="lightgreen" >파일첨부 첨부:  </td>
	<td align="center"> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td> 
</tr>
<tr>
	      <td align="right"> </td>
	      <td colspan="2">
	       <input type="submit" value="글쓰기" />
	       <input type=button value="목록보기"onClick="backToList(this.form)" />
	      </td>
</table>
</form>
</body>
</html>