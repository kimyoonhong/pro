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
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table border="1"  align="center"  width="80%">
<tr align="center">
<td>글번호</td>
<td>제목</td>
<td>작성자</td>
<td>작성일</td>
</tr>
<c:forEach var="noticeList" items="${noticeList}" varStatus="a" > 
<tr align="center">
<td>${a.count}</td>
<td><a href="${contextPath}/admin/noticeDetail.do?NOTICE_CODE=${noticeList.NOTICE_CODE}">${noticeList.NOTICE_TITLE}</a></td>
<td>${noticeList.ADMIN_NAME}</td>
<td>${noticeList.NOTICE_WRITEDATE}</td>
<c:choose> 
	<c:when test="${isLogOn == true  && admin!= null}">
     <td><a href="${contextPath}/admin/removenotice.do?NOTICE_CODE=${noticeList.NOTICE_CODE}">삭제하기</a></td>
     </c:when>
</c:choose>
</tr>
</c:forEach>
</table>
<c:choose> 
	<c:when test="${isLogOn == true  && admin!= null}">
     <h1><a href="${contextPath}/admin/noticeadd.do">공지사항작성</a></h1>
     </c:when>
</c:choose>

</body>
</html>