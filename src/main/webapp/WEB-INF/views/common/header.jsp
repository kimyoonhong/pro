<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
<table border=0  width="100%">
  <tr>
     <td>
		<a href="${contextPath}/listMembers.do">
		  <img src="${contextPath}/resources/image/duke_swing.gif"  />
		</a>
     </td>
     <td>
       <h1><font size=30>스프링실습 홈페이지!!</font></h1>
     </td>
     <td>
       <!-- isLogOn 속성 값을 체크하여 로그인 상태 시 로그아웃이 표시 되게 한다. -->
       <c:choose>
          <c:when test="${isLogOn == true  && member!= null}">
          <!-- 컨트롤러에서 member변수에 VO객체를 세션으로 보내준다. -->
            <h3>환영합니다. ${member.MEMBER_NAME}님!</h3>
            <a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>
            <!-- 로그아웃 링크를 클릭하면 로그인을 표시. -->
          </c:when>
          <c:otherwise>
	        <a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
	        <a href="${contextPath}/member/memberForm.do"><h3">회원가입</h3></a>
	        <!-- 로그인 링크를 클릭하면 로그인창 요청. -->
	      </c:otherwise>
	   </c:choose>   
	   <!-- isLogOn 속성 값을 체크하여 로그인 상태 시 로그아웃이 표시 되게 한다. -->  
     </td>    
  </tr>
</table>
</body>
</html>