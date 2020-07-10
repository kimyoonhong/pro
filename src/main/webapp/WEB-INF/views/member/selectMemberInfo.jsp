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
	<table border="1"  align="center"  width="80%">
    <tr align="center"   bgcolor="lightgreen">
      <td><b>이름</b></td>
      <td><b>성별</b></td>
      <td><b>휴대전화번호</b></td>
      <td><b>전공</b></td>
      <td><b>자기소개</b></td>
      <td colspan="4"><b>관심태그</b></td> 
     </tr>
     <c:forEach var="mem" items="${selectMemberInfo}" >     
    <tr align="center">
      <td>${mem.MEMBER_NAME}</td>
      <td>${mem.MEMBER_GENDER}</td>
      <td>${mem.HP1}-${mem.HP2}-${mem.HP3}</td>
      <td>${mem.MEMBER_JOB}</td>
      <td>${mem.MEMBER_CONTENT}</td>
     </c:forEach>
     <c:forEach var="tag" items="${selectTagList}" varStatus="t"> 
     <td>${tag.TAG}</td>
     </c:forEach> 
    </tr>
    
</body>
</html>