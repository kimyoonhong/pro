<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="frmTag">
	<table border="1"  align="center"  width="100%">
	   	<th>나의 프로젝트 리스트</th>
	   	<tr>
		   	<td align="center">
		   	 	프로젝트 코드
		   	</td>
		   	<td align="center">
		   		신청여부
		   	</td>
		   	<td align="center">
		   		신청날짜
		   	</td>
		   	<td align="center">
		   		합격여부
		   	</td>
	   	</tr>
   	<c:forEach var="myProjectList" items="${myProjectList}">
	   	<tr align="center">
		   	<td>${myProjectList.PROJECT_CODE}</td>
		   	<td>${myProjectList.APPLY_CK}</td>
		   	<td>${myProjectList.APPLY_DAY}</td>
		    <td>${myProjectList.PASS_CK}</td>
	   	</tr>
  	 </c:forEach>
   </table>
</form>
</body>
</html>