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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset=UTF-8">
<title>회원 정보 출력창</title>
<script type="text/javascript">
function fn_ck(){
	var isSeasonChk =false;
	var selecttag =document.getElementsByName("selecttag");

	if(!isSeasonChk){
		selecttag[0].checked =true;
		selecttag[0].value = "null";
		return false;
	}
}


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
   
 <c:forEach var="project" items="${projectsList}" >     
   <tr align="center">
      <td>${project.MEMBER_ID}</td>
      <td>${project.PROJECT_CODE}</td>
      <td>${project.PROJECT_TITTLE}</td>
      <td>${project.PROJECT_CONTENT}</td>
      <td>${project.PROJECT_REGISTER_DAY}</td>
      <td>${project.INVITE_STRDAY}</td>
      <td>${project.INVITE_ENDDAY}</td>
      <td>${project.PROJECT_STRDAY}</td>
      <td>${project.PROJECT_ENDDAY}</td>
      <td>${project.TOTALMEMBER}</td>
      <td>${project.APPLYMEMBER}</td>
      <td>${project.PASSMEMBER}</td>
      <td><a href="${contextPath}/project/removeproject.do?PROJECT_CODE=${project.PROJECT_CODE}">삭제하기</a></td>
    </tr>
  </c:forEach>   
</table>
<a  href="${contextPath}/project/projectadd.do"><h1 style="text-align:center">프로젝트 작성</h1></a>

<form name="frmSearch" action="${contextPath}/project/searchprojects.do" method = "get">
<table border="1"  align="center"  width="80%">
<tr align="center"   bgcolor="lightgreen">
<td>대분류</td>
<td>중분류</td>
<td>소분류</td>
</tr>
<c:forEach var="tag" items="${projectTagList}">
<tr align="center">
<td>${tag.TAG_FIRST}</td>
<td>${tag.TAG_SECOND}</td>
<td>${tag.TAG_THIRD}</td>
<td><input type="checkbox" id="selecttag" name="selecttag" value="${tag.TAG_THIRD}"></td>
</tr>
</c:forEach>
</table>
	<input name="searchWord" class="main_input" type="text" > 
	<input type="submit" name="search" class="btn1"  value="검색"  onClick="fn_ck()" >
</form>
</body>
</html>
