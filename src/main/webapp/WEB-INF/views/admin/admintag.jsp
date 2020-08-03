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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  

<style>
	#in input{
		width: 80%;
	}
</style>
<script>
function admin_tag(){
	
	var TAG_FIRST = document.getElementById("TAG_FIRST").value;
	var TAG_SECOND = document.getElementById("TAG_SECOND").value;
	var TAG_THIRD = document.getElementById("TAG_THIRD").value;
	
	if(TAG_FIRST !="" && TAG_SECOND!="" && TAG_THIRD !=""){
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/admin/addtag.do",
		data : {TAG_FIRST:TAG_FIRST,TAG_SECOND:TAG_SECOND,TAG_THIRD:TAG_THIRD},
		success : function(data) {
			alert(data);
		},
		error : function(data) {
			alert("에러가 발생했습니다.");
		},
		complete : function(data, textStatus) {
			//alert("작업을완료 했습니다");
			
		}
	}); //end ajax	
	 history.go(0);
	}else 
		alert("카테고리를 대분류 중분류 소분류 선택해주세요");
}
</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>테그 리스트</h1>
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
<td><a href="${contextPath}/admin/removetag.do?TAG_THIRD=${tag.TAG_THIRD}">삭제하기</a></td>
</tr>
</c:forEach>
</table>
<h1>테그 추가하기</h1>
<div id="in">
<table border="1"  align="center"  width="80%">
<tr>
<td>대분류</td>
<td>중분류</td>
<td>소분류</td>
</tr>
<tr>
	<td><input type="text" id="TAG_FIRST" name="TAG_FIRST"></td>
	<td><input type="text" id="TAG_SECOND" name="TAG_FIRST"></td>
	<td><input type="text" id="TAG_THIRD" name="TAG_FIRST"></td>
	<td><input type="button" id="admin" onClick="admin_tag()" value="추가하기"></td>
</tr>
</table>
</div>
</body>
</html>