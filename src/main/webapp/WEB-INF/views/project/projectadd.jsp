<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
<script type="text/javascript">


   function readURL(input) {
      if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }  
  function backToList(obj){
    obj.action="${contextPath}/project/listprojects.do";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
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
/*   $(function(){
		var isSeasonChk =false;
		var selecttag =document.getElementsByName("selecttag");
		for(var i=0;i<selecttag.length; i++){
			if(selecttag[i].checked == true){
				isSeasonChk = true;
				break;
			}
		}
		if(!isSeasonChk){
			selecttag[0].checked =true;
			selecttag[0].value = "null";
			return false;
		}
	}); */


</script>
 <title>프로젝트 등록</title>
</head>
<body>
<h1 style="text-align:center">프로젝트 작성</h1>
  <form name="addproject" method="post"   action="${contextPath}/project/addproject.do"   enctype="multipart/form-data">
    <table border="0" align="center">
    
      <tr>
					<td align="right"> 작성자</td>
					<td colspan=2  align="left"><input type="text" size="20" maxlength="100"  value="${memberInfo.MEMBER_NAME}" readonly/> </td>
					<input type="hidden" name="MEMBER_ID" type="text" value="${memberInfo.MEMBER_ID}" />
			</tr>
	     <tr>
			   <td align="right">프로젝트 제목: </td>
			   <td colspan="2"><input type="text" size="67"  maxlength="500" name="PROJECT_TITTLE" /></td>
		 </tr>
		 <tr>
		 	  <td align="right">프로젝트 일정: </td>
			  <td>시작 날짜<input type="text" id="testDatepicker0" name="PROJECT_STRDAY"  value="미정" readonly> </td>
			  <td>종료 날짜 <input type="text" id="testDatepicker1" name='PROJECT_ENDDAY' value="미정"  readonly> </td>
			  <td><input type="checkbox" id="DAY_CK0" name="DAY_CK0" value="미정"> 미정</td>
		 </tr>
		 		<input type="hidden"  id="selecttag" name="selecttag" value="null">
		 
		 <tr><td>카테고리</td></tr>
		<tr align="center"   bgcolor="lightgreen"> 
		<td>대분류</td>
		<td>중분류</td>
		<td>소분류</td>
		<td>선택</td>
		</tr>
		<c:forEach var="tag" items="${projectTagList}">
		<tr align="center">
		<td>${tag.TAG_FIRST}</td>
		<td>${tag.TAG_SECOND}</td>
		<td>${tag.TAG_THIRD}</td>
		<td><input type="checkbox" id="selecttag" name="selecttag" value="${tag.TAG_THIRD}"></td>
		</tr>
		</c:forEach>
		 <tr>
		 	  <td align="right">모집 기간: </td>
			  <td>모집날짜 <input type="text" id="testDatepicker2" name="INVITE_STRDAY" value="상시모집"  readonly> </td>
			  <td>모집종료날짜 <input type="text" id="testDatepicker3" name="INVITE_ENDDAY" value="상시모집" readonly> </td>
			  <td><input type="checkbox" id="DAY_CK1" name="DAY_CK1" value="미정"> 상시모집</td>
		 </tr>
	     <tr>
			   <td align="right">모집 인원: </td>
			   <td colspan="2">
			   <select  onchange="Check_Email()" id="dd" name="TOTALMEMBER">
			   <option value="미정">선택하세요</option>
			   <option value="1">1</option>
			   <option value="2">2</option>
			   <option value="3">3</option>
			   <option value="4">4</option>
			   <option value="5">5</option>
			   <option value="6">6</option>
			   <option value="7">7</option>
			   <option value="8">8</option>
			   <option value="9">9</option>
			   <option value="10">10</option>
			   </select> 명</td>			   
		 </tr>
	 <tr>
				<td align="right" valign="top"><br>글내용: </td>
				<td colspan=2><textarea name="PROJECT_CONTENT" rows="10" cols="65" maxlength="4000"></textarea> </td>
     </tr>
	   <tr>
			  <td align="right">이미지파일 첨부:  </td>
			  <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
			  <td><img  id="preview" src="#"   width=200 height=200/></td>
			  <td align="right">이미지파일 첨부</td>
				<td align="left"> <input type="button" value="파일 추가" onClick="fn_addFile()"/></td>
	   </tr>
	   <tr>
	      <td colspan="4"><div id="d_file"></div></td>
	   </tr>
	    <tr>
	      <td align="right"> </td>
	      <td colspan="2">
	       <input type="submit" value="글쓰기" />
	       <input type=button value="목록보기"onClick="backToList(this.form)" />
	      </td>
     </tr>
    </table>
  </form>
</body>
</html>
