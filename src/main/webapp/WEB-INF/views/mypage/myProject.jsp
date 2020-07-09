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
<form>
 <table border="1"  align="center"  width="100%">
	   	<th>신청중 프로젝트 리스트</th>
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
		   	<td align="center">
		   		프로젝트 진행 상태
		   	</td>
	   	</tr>
   	<c:forEach var="ApplyProjectList" items="${ApplyProjectList}">
   		<c:choose>
          <c:when test="${ApplyProjectList.APPLY_CK == '신청중'}">
	   	<tr align="center">
		   	<td>
		   	<a class='cls1' href="${contextPath}/project/projectDetail.do?PROJECT_CODE=
   			${ApplyProjectList.PROJECT_CODE}">${ApplyProjectList.PROJECT_CODE}</a>
   			</td>
		   	</td>
		   	<td>${ApplyProjectList.APPLY_CK}</td>
		   	<td>${ApplyProjectList.APPLY_DAY}</td>
		    <td>${ApplyProjectList.PASS_CK}</td>
		    <td>${ApplyProjectList.PROJECT_STATE}</td>
		    <td>
		   	<a class='cls1' href="${contextPath}/mypage/cancel.do?PROJECT_CODE=
   			${ApplyProjectList.PROJECT_CODE}&MEMBER_ID=${ApplyProjectList.MEMBER_ID}">신청취소</a>
   			</td>    
	   	</tr>
	   	</c:when>
	   	</c:choose>
  	 </c:forEach>
   </table>	
</form> 

<form>
 <table border="1"  align="center"  width="100%">
	   	<th>내가 찜한 프로젝트 리스트</th>
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
		   	<td align="center">
		   		프로젝트 진행 상태
		   	</td>
	   	</tr>
   	<c:forEach var="ApplyProjectList" items="${ApplyProjectList}">
   		<c:choose>
          <c:when test="${ApplyProjectList.APPLY_CK == '관심'}">
	   	<tr align="center">
		   	<td>
		   	<a class='cls1' href="${contextPath}/project/projectDetail.do?PROJECT_CODE=
   			${ApplyProjectList.PROJECT_CODE}">${ApplyProjectList.PROJECT_CODE}</a>
   			</td>
		   	<td>${ApplyProjectList.APPLY_CK}</td>
		   	<td>${ApplyProjectList.APPLY_DAY}</td>
		    <td>${ApplyProjectList.PASS_CK}</td>
		    <td>${ApplyProjectList.PROJECT_STATE}</td>
		    <td>
		   	<a class='cls1' href="${contextPath}/mypage/cancel.do?PROJECT_CODE=
   			${ApplyProjectList.PROJECT_CODE}&MEMBER_ID=${ApplyProjectList.MEMBER_ID}">신청취소</a>
   			</td>    
	   	</tr>
	   	</c:when>
	   	</c:choose>
  	 </c:forEach>
   </table>	
</form>


<form name="frmTag">
	<table border="1"  align="center"  width="100%">
	   	<th>내가 만든 프로젝트 리스트</th>
	   	<tr>
		   	<td align="center">
		   	 	제목
		   	</td>
		   	<td align="center">
		   		등록 일 
		   	</td>
		   	<td align="center">
		   		지원자 수
		   	</td>
		   	<td align="center">
		   		합격자 수
		   	</td>
		   	<td align="center">
		   		모집 인원
		   	</td>
		   	<td align="center">
		   		프로젝트 진행 상태
		   	</td>
	   	</tr>
   	<c:forEach var="MyProjectList" items="${MyProjectList}">
	   	<tr align="center">
		   	<td>
		   	<a class='cls1' href="${contextPath}/project/projectDetail.do?PROJECT_CODE=
   			${MyProjectList.PROJECT_CODE}">${MyProjectList.PROJECT_TITTLE}</a>
   			</td>
		   	<td>${MyProjectList.PROJECT_REGISTER_DAY}</td>
		   	<td>${MyProjectList.APPLYMEMBER}</td>
		    <td>${MyProjectList.PASSMEMBER}</td>
		    <td>${MyProjectList.TOTALMEMBER}</td>
		    <td>${MyProjectList.PROJECT_STATE}</td>
	   	</tr>
  	 </c:forEach>
   </table>
</form>


</body>
</html>