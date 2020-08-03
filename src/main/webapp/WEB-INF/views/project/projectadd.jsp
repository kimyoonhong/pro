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
  function DAY_CK(ck){

		if(ck=='inv'){
			document.getElementById("testDatepicker2").value = "상시모집";
			document.getElementById("testDatepicker3").value = "상시모집";
		}
		
		if(ck=='pro'){
			document.getElementById("testDatepicker0").value = "미정";
			document.getElementById("testDatepicker1").value = "미정";
		}
		
	}


</script>
<style>
.form{
    padding-left: 250px;
    padding-right: 250px;
	background-color: #ffffff;
	}
	
.form_box {
    background-color: #ffffff;
    margin: 8px;
    border-radius: 4px;
    border: 1px solid #ddd;
    padding-left: 10px;
    padding-right: 10px;
	}
	
.input_field {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 4px;
    margin: 3px 0;
    font-size: 14px;
    width: 90%;
	}
.textarea_field {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 4px;
    margin: 3px 0;
    font-size: 14px;
    width: 90%;
    height: 280px;
	}
.write_button {
    background-color: #475d9f;
    border: 1px solid #323f6b;
    color: #ffffff;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 15px;
	}
.back_button{
    background-color: #475d9f;
    border: 1px solid #323f6b;
    color: #ffffff;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 15px;
	}
.out_field {
    /*border: 1px solid #ddd;*/
   	float: left;
    border-radius: 4px;
    padding: 4px;
    margin: 5px 0;
    font-size: 14px;
    width: 15%;
   	}
	
.fixed_left{
	float:left;
	}	
.flex{
	float: left;
	}
	

#contents_tb td, th {
    border: 3px;
    padding: 1px;
	font-gamily: "Nanum Gothic"
	font-size:14px;

	}

#contents_tb th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;

    color: white;
}

</style>
 <title>프로젝트 등록</title>
</head>
<body>
<h3 style="text-align:center">프로젝트 작성</h3>
  <form name="addproject" method="post"   action="${contextPath}/project/addproject.do"   enctype="multipart/form-data">

	<table id="contents_tb"  border="0" bgcolor = "#809fff" align="center">
	<tbody class="tbody">
    <!-- <table > -->
    
      <tr>
           <td align="right"> 작성자:</td>
           <td colspan=2  align="left"><input type="text" size="20" maxlength="100"  value="${member.MEMBER_NAME}" readonly/> </td>
           <input class="fixed_left" type="hidden" name="MEMBER_ID" type="text" value="${member.MEMBER_ID}" />
         </tr>
        <tr>
            <td align="right">프로젝트 제목: </td>
            <td colspan="2"><input class="fixed_left" type="text" size="67"  maxlength="500" name="PROJECT_TITTLE" /></td>
       </tr>
       <tr>
           <td align="right">프로젝트 일정: </td>
           <td>시작일<input type="text" id="testDatepicker0" name="PROJECT_STRDAY"  value="미정" readonly> </td>
           <td>종료일 <input type="text" id="testDatepicker1" name='PROJECT_ENDDAY' value="미정"  readonly> </td>
           <td><input type="button" id="DAY_CK0" name="DAY_CK0" onClick="DAY_CK('pro')" value="미정"></td>
       </tr>
     <input type="hidden"  id="selecttag" name="selecttag" value="null">
      <tr align="center" bgcolor="#0066ff">	<!--    bgcolor="lightgreen" --> 
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
           <td  align="right">시작일 <input type="text" id="testDatepicker2" name="INVITE_STRDAY" value="상시모집"  readonly> </td>
           <td>종료일 <input type="text" id="testDatepicker3" name="INVITE_ENDDAY" value="상시모집" readonly> </td>
 <td><input type="button" id="DAY_CK1" name="DAY_CK1" onClick="DAY_CK('inv')" value="상시모집"></td>
       </tr>
        <tr>
            <td align="right">모집 인원: </td>
            <td colspan="2">
            <select class="fixed_left" id="dd" name="TOTALMEMBER">
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
            </select><a class="flex">&nbsp명</a></td>      
                  
       </tr>
    <tr>
            <td align="right" valign="top"><br>프로젝트내용: </td>
            <td colspan=2><textarea class="textarea_field" name="PROJECT_CONTENT" rows="10" cols="65" maxlength="4000" placeholder="프로젝트 자세하게 작성 부탁드려요."></textarea> </td>
     </tr>
      <tr>
           <td align="right">파일첨부 첨부:  </td>
           <td> <input  type="file" name="imageFileName"  onchange="readURL(this);" /></td>
          
      </tr>
      <tr>
         <td colspan="4"><div id="d_file"></div></td>
      </tr>
       <tr>
         <td align="right"> </td>
         <td colspan="2">
          <input class="write_button"  type="submit"  onClick="DAY_CK()" value="글쓰기" />
          <input class="back_button"  type=button value="목록보기"onClick="backToList(this.form)" />
         </td>
     </tr>
     </tbody>
    </table>
  </form>
</body>
</html>

