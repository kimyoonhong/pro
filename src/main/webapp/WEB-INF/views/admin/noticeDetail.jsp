<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="result" value="${param.result }" />
<%
   request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   </style>
   <script>
   function backToList(obj){
	    obj.action="${contextPath}/admin/listnotice.do";
	    obj.submit();
    }
   
   function fn_enable(obj){
	   document.getElementById("i_NOTICE_CODE").disabled=false;
		 document.getElementById("i_NOTICE_TITLE").disabled=false;
		 document.getElementById("i_NOTICE_CONTENT").disabled=false;
		document.getElementById("i_NOTICE_FILENAME").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
   
   function fn_remove_notice(url,NOTICE_CODE){
		 var form = document.createElement("form");
		 
		 form.setAttribute("method", "GET");
		 form.setAttribute("action", url);
	     var NOTICE_CODEInput = document.createElement("input");
	     PROJECT_CODEInput.setAttribute("type","hidden");
	     PROJECT_CODEInput.setAttribute("name","NOTICE_CODE");
	     PROJECT_CODEInput.setAttribute("value", NOTICE_CODE);
		
	     form.appendChild(NOTICE_CODEInput);
	     document.body.appendChild(form);
	     form.submit();
	 }
   
   function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }
   
   function fn_modify_notice(obj){
		 obj.action="${contextPath}/admin/updatenotice.do";
		 obj.submit();
	 }
   </script>
   <style>
   	#wap{
   	margin-left:33%;
   	}
   </style>
</head>
<body>
<div id="wap">
<form name="frmnotice" method="post"  action="${contextPath}"  enctype="multipart/form-data">
<table>
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
     공지사항 코드
   </td>
   <td >
    <input type="text"  id="i_NOTICE_CODE" name="NOTICE_CODE" value="${notice.NOTICE_CODE}"  disabled readonly/>
   </td>
  </tr>
	<tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자 이름
   </td>
   <td>
    <input type=text value="${notice.ADMIN_NAME}" name="writer"  disabled />
   </td>
	</tr>
	<tr>
	<tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${notice.NOTICE_TITLE}"  name="NOTICE_TITLE"  id="i_NOTICE_TITLE" disabled />
   </td>   
  </tr>
  <tr> 
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="NOTICE_CONTENT"  id="i_NOTICE_CONTENT"  disabled />${notice.NOTICE_CONTENT}</textarea>
   </td>  
  </tr>
	
	   	<tr>
		    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
		      첨부파일
		   </td>
		  <c:choose> 
	  <c:when test="${not empty notice.NOTICE_FILENAME && notice.NOTICE_FILENAME!='null' }">
		   <td>
		     <input  type= "hidden"   name="originalFileName" value="${notice.NOTICE_FILENAME}" />
		    <a href="${contextPath}/noticedownload.do?NOTICE_CODE=${notice.NOTICE_CODE}&NOTICE_FILENAME=${notice.NOTICE_FILENAME}"  id="preview"  >${notice.NOTICE_FILENAME}</a><br>
		    <a href="${contextPath}/admin/removenoticefile.do?NOTICE_CODE=${notice.NOTICE_CODE}&originalFileName=${notice.NOTICE_FILENAME}">삭제하기</a>
		   </td>  
		   </c:when>
		  	 </c:choose>	 
		  </tr>  
		  <tr>
		    <td>
		   	<input  type= "hidden"   name="originalFileName" value="${notice.NOTICE_FILENAME}" />
		    <input  type="file"  name="NOTICE_FILENAME" id="i_NOTICE_FILENAME" disabled onchange="readURL(this);"   />
		    </td>
		  </tr>

  <tr   id="tr_btn_modify"  align="center"  >
	   <td colspan="2"   >
	       <input type=button value="수정반영하기"   onClick="fn_modify_notice(frmnotice)"  >
           <input type=button value="취소"  onClick="backToList(frmnotice)">
	   </td>   
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
       <c:if test="${admin.ADMIN_NAME== notice.ADMIN_NAME}">
	      <input type=button value="수정하기" onClick="fn_enable(this.form)">
	      <input type=button value="삭제하기" onClick="fn_remove_notice('${contextPath}/project/removenotice.do', ${notice.NOTICE_CODE})">
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
   </td>
  </tr>
 
</table>
</form>
</div>
</body>
</html>