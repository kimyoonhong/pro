<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%-- <c:set var="project"  value="${viewprojectList}"/>
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  /> --%>

<%
  request.setCharacterEncoding("UTF-8");
%> 
  
<head>
   <meta charset="UTF-8">
   <title>프로젝트 글보기</title>
  
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  

<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   	#tag{
   	display:none;
   	}
  
   </style>
   
   <script type="text/javascript" >
   	
     function backToList(obj){
    	 
	    obj.action="${contextPath}/project/listprojects.do";
	    obj.submit();
     }
 	
	 function fn_enable(obj){
		 document.getElementById("i_PROJECT_TITTLE").disabled=false;
		 document.getElementById("PROJECT_CONTENT").disabled=false;
		 document.getElementById("testDatepicker0").disabled=false;
		 document.getElementById("testDatepicker1").disabled=false;
		 document.getElementById("testDatepicker2").disabled=false;
		 document.getElementById("testDatepicker3").disabled=false;
		 document.getElementById("TOTALMEMBER").disabled=false;
		 document.getElementById("PASSMEMBER").disabled=false;
		 document.getElementById("APPLYMEMBER").disabled=false;

		//document.getElementById("PROJECT_FILENAME").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tag").style.display="block";
		 //document.getElementById("tag1").style.display="block";
		//document.getElementById("i_imageFileName").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_project(obj){
		 obj.action="${contextPath}/project/updateproject.do";
		 obj.submit();
	 }
	 
	 function fn_remove_project(url,PROJECT_CODE){
		 var form = document.createElement("form");
		 
		 form.setAttribute("method", "GET");
		 form.setAttribute("action", url);
	     var PROJECT_CODEInput = document.createElement("input");
	     PROJECT_CODEInput.setAttribute("type","hidden");
	     PROJECT_CODEInput.setAttribute("name","PROJECT_CODE");
	     PROJECT_CODEInput.setAttribute("value", PROJECT_CODE);
		
	     form.appendChild(PROJECT_CODEInput);
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

	function fn_modify_tag(PROJECT_TAG){
			//var a = $(".c_project_tag").index(this);
				
			    
		//var PROJECT_TAG = document.getElementById("i_PROJECT_TAG").value;
		var PROJECT_CODE = document.getElementById("i_PROJECT_CODE").value;
		var PROJECT_TITTLE = document.getElementById("i_PROJECT_TITTLE").value;
				
		alert(PROJECT_TAG);

			$.ajax({
				type : "post",
				async : false, //false인 경우 동기식으로 처리한다.
				url : "${contextPath}/project/addprojecttag.do",
				data : {PROJECT_TAG:PROJECT_TAG,PROJECT_CODE:PROJECT_CODE,PROJECT_TITTLE:PROJECT_TITTLE},
				success : function(data) {
					alert(data);
				},
				error : function(data, textStatus) {
					alert("에러가 발생했습니다.");
				},
				complete : function(data, textStatus) {
					//alert("작업을완료 했습니다");
					
				}
			}); //end ajax	
			 history.go(0);
		}
		$(function() {
		  for(i=0; i<4; i++){
		    $("#testDatepicker"+i).datepicker({
		         changeMonth: true, 
		         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
		         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
		         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		  });
		  }
		  
		});
		 function fn_apply_project(APPLY_CKC,MEMBER_ID){
			 var MEMBER_ID = MEMBER_ID;
			 alert(MEMBER_ID);
			 if(MEMBER_ID==null){
				 alert("로그인해라");	
			 }
			 else if(MEMBER_ID!=null){
				 if(APPLY_CKC=='신청하기'){
						var APPLY_CK='신청중';
					}else if(APPLY_CKC=='관심등록'){
						var APPLY_CK='관심';
					}
					alert(APPLY_CK);
				 var PROJECT_CODE =document.getElementById("i_PROJECT_CODE").value; 
				 $.ajax({
						type : "post",
						async : false, //false인 경우 동기식으로 처리한다.
						url : "${contextPath}/project/memberprojectadd.do",
						data : {APPLY_CK:APPLY_CK,PROJECT_CODE:PROJECT_CODE,MEMBER_ID:MEMBER_ID},
						success : function(data) {
							alert(data);
						},
						error : function(data) {
							alert("에러가 발생했습니다.");
						},
						complete : function(data, textStatus) {
							//alert("작업을완료 했습니다");
							
						}
					});
			 }
			 
			 
		 }
	 
 </script>
</head>
<body>

  <form name="frmproject" method="post"  action="${contextPath}"  enctype="multipart/form-data">
  <table  border=0  align="center">
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      글번호
   </td>
   <td >
    <input type="text"  value="${project.PROJECT_CODE}"  disabled />
    <input type="hidden" id="i_PROJECT_CODE" name="PROJECT_CODE" value="${project.PROJECT_CODE}"  />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자 아이디
   </td>
   <td>
    <input type=text value="${project.MEMBER_ID}" name="writer"  disabled />
   </td>
	</tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${project.PROJECT_TITTLE}"  name="PROJECT_TITTLE"  id="i_PROJECT_TITTLE" disabled />
   </td>   
  </tr>
  <tr>
  <td align="center" bgcolor="#FF9933">프로젝트  </td>
  <td>프로젝트 시작일</td>
  <td>프로젝트 마감일</td>
  </tr>
  <tr>
  <td align="center" bgcolor="#FF9933">일정 </td>
  <td> <input type="text" id="testDatepicker0" name="PROJECT_STRDAY" value="${project.PROJECT_STRDAY}" disabled readonly> </td>  
  <td> <input type="text" id="testDatepicker1" name='PROJECT_ENDDAY' value="${project.PROJECT_ENDDAY}" disabled readonly> </td>
  </tr>
  <tr> 
  <tr>
  <td align="center" bgcolor="#FF9933">모집  </td>
  <td>모집 시작일</td>
  <td>모집 마감일</td>
  </tr>
  <tr>
  <td align="center" bgcolor="#FF9933">기간 </td>
  <td> <input type="text" id="testDatepicker2" name="INVITE_STRDAY" value="${project.INVITE_STRDAY}"  disabled readonly> </td>  
  <td> <input type="text" id="testDatepicker3" name='INVITE_ENDDAY' value="${project.INVITE_ENDDAY}"  disabled readonly> </td>
  </tr>
  <tr> 
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="PROJECT_CONTENT"  id="PROJECT_CONTENT"  disabled />${project.PROJECT_CONTENT}</textarea>
   </td>  
  </tr>
 <tr>
 <td>모집인원</td>
 		<td><input type="text" id=TOTALMEMBER name=TOTALMEMBER value="${project.TOTALMEMBER }" disabled></td>
 </tr>
 <tr>
 <td>지원인원</td>
 		<td><input type="text" id=APPLYMEMBER name=APPLYMEMBER value="${project.APPLYMEMBER }" disabled readonly></td>
 </tr>
 <tr>
 <td>합격인원</td>
 		<td><input type="text" id=PASSMEMBER name=PASSMEMBER value="${project.PASSMEMBER }" disabled ></td>
 </tr>
 	 
  <c:choose> 
	  <c:when test="${not empty project.PROJECT_FILENAME && project.PROJECT_FILENAME!='null' }">
	   	<tr>
		    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
		      이미지
		   </td>
		   <td>
		     <input  type= "hidden"   name="PROJECT_FILENAME" value="${project.PROJECT_FILENAME}" />
		    <a href="${contextPath}/download.do?PROJECT_CODE=${project.PROJECT_CODE}&PROJECT_FILENAME=${project.PROJECT_FILENAME}"  id="preview"  >${project.PROJECT_FILENAME}</a><br>
		   </td>   
		  </tr>  
		  <tr>
		    <td ></td>
		    <td>
		       <input  type="file"  name="PROJECT_FILENAME " id="PROJECT_FILENAME"   disabled   onchange="readURL(this);"   />
		    </td>
		  </tr> 
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
				    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
				      이미지
				    </td>
				    <td>
				      <input  type= "hidden"   name="PROJECT_FILENAME" value="${project.PROJECT_FILENAME}" />
				    </td>
			    </tr>
			    <tr>
				    <td ></td>
				    <td>
				       <img id="preview"  /><br>
				       <input  type="file"  name="PROJECT_FILENAME " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
				    </td>
			  </tr>
		 </c:otherwise>
	 </c:choose>
  <tr>
  <tr>
	   <td width="150" align="center" bgcolor="#FF9933">
	      등록일자
	   </td>
	   <td>
	    <input type=text value="${project.PROJECT_REGISTER_DAY}"  readonly disabled />
	   </td>   
  </tr> 
  <tr   id="tr_btn_modify"  align="center"  >
	   <td colspan="2"   >
	       <input type=button value="수정반영하기"   onClick="fn_modify_project(frmproject)"  >
           <input type=button value="취소"  onClick="backToList(frmproject)">
	   </td>   
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
       <c:if test="${member.MEMBER_ID == project.MEMBER_ID}">
	      <input type=button value="수정하기" onClick="fn_enable(this.form)">
	      <input type=button value="삭제하기" onClick="fn_remove_project('${contextPath}/project/removeproject.do', ${project.PROJECT_CODE})">
	    </c:if>
	    <c:if test="${member.MEMBER_ID != project.MEMBER_ID}">
	     <input type="button" value="신청하기" onClick="fn_apply_project(this.value,'${member.MEMBER_ID}')">
	     <input type="button" value="관심등록" onClick="fn_apply_project(this.value,'${member.MEMBER_ID}')">
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
   </td>
  </tr>
  
 </table>
 </form>
	
 <form name=fn_modify_tags id="tag">
 <table border="1"  align="center"  width="100%">
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
	<td><input type="hidden" id="i_PROJECT_TAG" class="c_project_tag" name="PROJECT_TAG" value="${tag.TAG_THIRD}"><a href="#" onClick="fn_modify_tag('${tag.TAG_THIRD}')">추가하기</a></td>
	<%-- <td><a href="${contextPath}/project/addtag.do">추가하기</a></td> --%>
<%--  	<td><input type="button" name="addtag" value="추가하기" onClick="fn_modify_tag(${tag.TAG_THIRD})"></td> --%>
	</tr>
	</c:forEach>
	</table>
	</form>
	<form name=fn_my_tag id="tag1">
	<table  border="1"  align="center"  width="100%">
	<tr align="center" bgcolor="lightgreen">
	<td>MyTag</td>
	</tr>
	<c:forEach var="MytagList" items="${MytagList}">
	<tr align="center">
	<td>${MytagList.PROJECT_TAG}</td>
	<c:if test="${member.MEMBER_ID == project.MEMBER_ID}">
	<td><a href="${contextPath}/project/removetag.do?PROJECT_CODE=${project.PROJECT_CODE}&PROJECT_TAG=${MytagList.PROJECT_TAG}">삭제하기</a></td>
	</c:if>
	</tr>
	</c:forEach>
	</table>
	</form>
	
</body>
</html>