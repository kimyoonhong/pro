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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

</script>
</head>
<body>
<table border="1"  align="center"  width="80%">
    <tr align="center"   bgcolor="lightgreen">
      <td><b>글번호</b></td>
      <td><b>제목</b></td>
      <td><b>작성일</b></td>
   </tr>
 <c:forEach var="search" items="${projectsList}" >
      
   <tr align="center">
    <td></td>
	  <td><a class='cls1' href="${contextPath}/project/projectDetail.do?PROJECT_CODE=${search.PROJECT_CODE}">${search.PROJECT_TITTLE }</a></td>
      <td>${search.PROJECT_REGISTER_DAY}</td>
    </tr>
  </c:forEach>   
</table>
</body>
</html>