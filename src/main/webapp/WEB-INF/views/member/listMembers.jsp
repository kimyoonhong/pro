<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    


<html>
<head>
<meta charset=UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
<table border="1"  align="center"  width="90%">
    <tr align="center"   bgcolor="lightgreen">
      <td><b>아이디</b></td>
      <td><b>비밀번호</b></td>
      <td><b>이름</b></td>
      <td><b>성별</b></td>
      <td><b>생년월일</b></td>
      <td><b>자기소개</b></td>
      <td><b>전공</b></td>
      <td><b>전화번호</b></td>
      <td><b>휴대전화</b></td>
      <td><b>SMS수신동의</b></td>
      <td><b>이메일</b></td>
      <td><b>이메일수신동의</b></td>
      <td><b>우편번호</b></td>
      <td><b>도로명주소</b></td>
      <td><b>지번주소</b></td>
      <td><b>나머지주소</b></td>
      <td><b>가입일</b></td>
   </tr>
   
 <c:forEach var="member" items="${membersList}" >     
   <tr align="center">
      <td>${member.MEMBER_ID}</td>
      <td>${member.MEMBER_PW}</td>
      <td>${member.MEMBER_NAME}</td>
      <td>${member.MEMBER_GENDER}</td>
      <td>${member.MEMBER_BIRTH}</td>
      <td>${member.MEMBER_CONTENT}</td>
      <td>${member.MEMBER_JOB}</td>
      <c:choose> 
       <c:when test="${member.TEL1 =='없음'}">
 	  <td>없음</td>
 	  </c:when>
      <c:when test="${member.TEL1 !='없음'}">
      <td>${member.TEL}</td>
 	  </c:when>
      </c:choose>
      <td>${member.HP}</td>
      <td>${member.SMSSTS_YN}</td>
         <c:choose> 
       <c:when test="${member.EMAIL1 =='없음'}">
 	  <td>없음</td>
 	  </c:when>
      <c:when test="${member.EMAIL1 !='없음'}">
     <td>${member.EMAIL}</td>
 	  </c:when>
      </c:choose>
      <td>${member.EMAILSTS_YN}</td>
      <td>${member.ZIPCODE}</td>
      <td>${member.ROADADDRESS}</td>
      <td>${member.JIBUNADDRESS}</td>
      <td>${member.NAMUJIADDRESS}</td>
      <td>${member.JOINDATE}</td>
      <td><a href="${contextPath}/member/removeMember.do?MEMBER_ID=${member.MEMBER_ID }">삭제하기</a></td>
    </tr>
  </c:forEach>   
</table>
</body>
</html>