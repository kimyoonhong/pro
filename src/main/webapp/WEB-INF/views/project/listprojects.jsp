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
<style>
 .abc{
 	display :none;
 }
 
</style>

<script>
function hh(id){
	 var a = document.getElementById(id);
	
	 if(a.style.display=="block"){
		 a.style.display="none";
	 }else{
		 a.style.display="block";
	 }
}
</script>

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

function admin_tag(){
	
	var TAG_FIRST = document.getElementById("TAG_FIRST").value;
	var TAG_SECOND = document.getElementById("TAG_SECOND").value;
	var TAG_THIRD = document.getElementById("TAG_THIRD").value;
	
	if(TAG_FIRST !="" && TAG_SECOND!="" && TAG_THIRD !=""){
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/admin/admintag.do",
		data : {TAG_FIRST:TAG_FIRST,TAG_SECOND:TAG_SECOND,TAG_THIRD:TAG_THIRD},
		success : function(data) {
			alert(data);
		},
		error : function(data) {
			alert("a에러가 발생했습니다.");
		},
		complete : function(data, textStatus) {
			//alert("작업을완료 했습니다");
			
		}
	}); //end ajax	
	 history.go(0);
	}else 
		alert("카테고리를 대분류 중분류 소분류 선택해주세요");
}

function count_ck(obj){
	var chkbox = document.getElementsByName("selecttag");
	var chkCnt = 0;
	for(var i=0;i<chkbox.length; i++){
		if(chkbox[i].checked){
		chkCnt++;
		}
	}
	if(chkCnt>5){
		alert("최대 5개까지의 태그를 선택할수있습니다.");
		obj.checked = false;
		return false;
	}
}

function tagtag(TAG_FIRST,TAG_SECOND){
	alert(TAG_FIRST);
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/project/tagtag.do",
		data : {TAG_FIRST:TAG_FIRST,TAG_SECOND:TAG_SECOND},
		success : function(data) {
			
		},
		error : function(data) {
			
		},
		complete : function(data, textStatus) {
			
		}
	}); //end ajax	
	history.go(0);
}
</script>
</head>
<body>
<div id="ww">
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
   </tr>
 
 <c:forEach var="project" items="${projectsList}" varStatus="a" >  
   <tr align="center">
      <td>${project.MEMBER_ID}</td>
      <td>${project.PROJECT_CODE}</td>
      <td><a href="${contextPath}/project/projectDetail.do?PROJECT_CODE=${project.PROJECT_CODE}">${project.PROJECT_TITTLE}</a></td>
      <td>${project.PROJECT_CONTENT}</td>
      <td>${project.PROJECT_REGISTER_DAY}</td>
      <td>${project.INVITE_STRDAY}</td>
      <td>${project.INVITE_ENDDAY}</td>
      <td>${project.PROJECT_STRDAY}</td>
      <td>${project.PROJECT_ENDDAY}</td>
      <td>${project.TOTALMEMBER}</td>
      <td>${project.APPLYMEMBER}</td>
      <td>${project.PASSMEMBER}</td>
      <c:choose> 
      <c:when test="${isLogOn == true  && admin!= null}">
     <td><a href="${contextPath}/project/removeproject.do?PROJECT_CODE=${project.PROJECT_CODE}">삭제하기</a></td>
     </c:when>
      </c:choose>
    </tr>
  </c:forEach>
</table>

<%-- 
<c:forEach var="tag_first" items="${tag_first}" varStatus="a">
<tr>
<td><input type="button" value="${tag_first.TAG_FIRST}" onClick="hh(this.value)"></td>
</tr> 
<div id="${tag_first.TAG_FIRST}" class="abc">
<c:forEach var="tag_secondlist" items="${tag_secondlist}">
<c:if test="${tag_secondlist.TAG_FIRST == tag_first.TAG_FIRST }">
<tr>
<td><input type="button" name="tagtag" value="${tag_secondlist.TAG_SECOND}"></td>
</tr>
<c:forEach var="tag" items="${projectTagList}">
<c:if test="${tag_secondlist.TAG_SECOND == tag.TAG_SECOND}">
<tr>
<td>${tag.TAG_THIRD}</td>
<td><input type="checkbox" id="selecttag" name="selecttag" onClick="count_ck(this)" value="${tag.TAG_THIRD}"></td>
</tr>
</c:if>
</c:forEach>
</c:if>
</c:forEach>
</div>
</c:forEach> --%>
</div>
</body>
</html>
