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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

</script>
</head>
<body>
<table border="1"  align="center"  width="80%">
    <tr align="center"   bgcolor="lightgreen">
      <td ><b>작성자</b></td>
      <td><b>프로젝트 코드</b></td>
      <td><b>제목</b></td>
      <td><b>내용</b></td>
      <td><b>작성일</b></td>
      <td><b>모집 시작일</b></td>
      <td><b>모집 종료일</b></td>
      <td><b>프로젝트 시작일</b></td>
      <td><b>프로젝트 종료일</b></td>
      <td><b>총원</b></td>
      <td><b>지원자수</b></td>
      <td><b>모집된 인원수</b></td>
      <td><b>삭제</b></td>
   </tr>
   
 <c:forEach var="search" items="${projectsList}" >     
   <tr align="center">
      <td>${search.MEMBER_ID}</td>
      <td>${search.PROJECT_CODE}</td>
      <td>${search.PROJECT_TITTLE}</td>
      <td>${search.PROJECT_CONTENT}</td>
      <td>${search.PROJECT_REGISTER_DAY}</td>
      <td>${search.INVITE_STRDAY}</td>
      <td>${search.INVITE_ENDDAY}</td>
      <td>${search.PROJECT_STRDAY}</td>
      <td>${search.PROJECT_ENDDAY}</td>
      <td>${search.TOTALMEMBER}</td>
      <td>${search.APPLYMEMBER}</td>
      <td>${search.PASSMEMBER}</td>
      <td><a href="${contextPath}/project/removeproject.do?PROJECT_CODE=${project.PROJECT_CODE}">삭제하기</a></td>
    </tr>
  </c:forEach>   
</table>
</body>
</html>