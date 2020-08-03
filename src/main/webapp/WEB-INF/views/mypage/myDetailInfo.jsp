<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.List" %>
<%@ page import ="com.myspring.pro.project.controller.projectControllerImpl"%>
<%@ page import ="com.myspring.pro.project.service.projectService"%>
<%@ page import ="com.myspring.pro.project.dao.projectDAO"%>
<%@ page import ="com.myspring.pro.project.vo.tagVO"%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

 <script>

 /* 이메일 체크 */
//이메일 입력방식 선택
function Check_Email() {
  var EMAIL2 = $("#EMAIL2").val(); // input 박스  값 
  var selectEmail = $("#selectEmail").val();// select박스 선택 값
       
  // 직접 입력 선택했을 경우
  if (selectEmail == '1'){
     $('#EMAIL1').prop("value",''); // input 박스 초기화
     $('#EMAIL2').prop("value",''); // input 박스 초기화
     $('#EMAIL1').prop("readonly",false);// input 박스 readonly 속성 비활성화
     $('#EMAIL2').prop("readonly",false);// input 박스 readonly 속성 비활성화
  }
  else if (selectEmail== '없음'){
     $('#EMAIL1').prop("value",'없음'); // 없음
     $('#EMAIL2').prop("value",'없음'); // 없음
     $('#EMAIL1').prop("readonly",true);// input 박스 readonly 속성 비활성화
     $('#EMAIL2').prop("readonly",true);// input 박스 readonly 속성 활성화
  }
  else {
  // 이메일 선택했을경우   
     $('#EMAIL1').prop("value",'');  // input 박스 초기화
     $('#EMAIL2').prop("value",selectEmail); // 선택 값 input박스로 전달
     $('#EMAIL1').prop("readonly",false);// input 박스 readonly 속성 비활성화
     $('#EMAIL2').prop("readonly",true); // input 박스 readonly 속성 활성화
     
  }
}
 	
 
	function execDaumPostcode() {
		  new daum.Postcode({
		    oncomplete: function(data) {
		      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		      // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
		      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		      var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
		      var extraRoadAddr = ''; // 도로명 조합형 주소 변수

		      // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		      // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		      if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		        extraRoadAddr += data.bname;
		      }
		      // 건물명이 있고, 공동주택일 경우 추가한다.
		      if(data.buildingName !== '' && data.apartment === 'Y'){
		        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		      }
		      // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		      if(extraRoadAddr !== ''){
		        extraRoadAddr = ' (' + extraRoadAddr + ')';
		      }
		      // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
		      if(fullRoadAddr !== ''){
		        fullRoadAddr += extraRoadAddr;
		      }

		      // 우편번호와 주소 정보를 해당 필드에 넣는다.
		      document.getElementById('ZIPCODE').value = data.zonecode; //5자리 새우편번호 사용
		      document.getElementById('ROADADDRESS').value = fullRoadAddr;
		      document.getElementById('JIBUNADDRESS').value = data.jibunAddress;

		      // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
		      if(data.autoRoadAddress) {
		        //예상되는 도로명 주소에 조합형 주소를 추가한다.
		        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		        document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

		      } else if(data.autoJibunAddress) {
		          var expJibunAddr = data.autoJibunAddress;
		          document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
		      } else {
		          document.getElementById('guide').innerHTML = '';
		      }	     
		    }
		  }).open();
		} 
   window.onload=function()
    {
      selectBoxInit();
    }
   // SELECT 박스 초기화 
    function selectBoxInit(){
    
     // TEL1, HP1, EMAIL2 기존 회원 정보 받아오기.
     var TEL1='${member.TEL1}';
     var HP1='${member.HP1}';
     var EMAIL2='${member.EMAIL2}';
     //var ProjectTagList='${ProjectTagList}';
     //var MemberTagList='${MemberTagList}';
     // SELECT 박스 값 초기화 하기위해 ID값 받아오기.
     var SELTEL1 = document.getElementById('TEL1'); // 전화번호
     var SELHP1 = document.getElementById('HP1');   // 휴대전화번호
     var SELEMAIL2 = document.getElementById('selectEmail'); // 이메일
     // 체크 박스 값 초기화 하기 위해 NAME 값 받아오기.
     //var ProjectTagList = document.getElementsByName('ProjectTagList');// 태그
     //var MemberTagList = document.getElementsByName('MemberTagList');// 태그
     
     // SELECT 박스 옵션값 받아오기.
     var OPTIONTEL1 = SELTEL1.options;
     var OPTIONHP1 = SELHP1.options;
     var OPTIONEMAIL2 = SELEMAIL2.options;
     var val;
     
     // 해쉬태그 
     // TAGVO()의 THIRD_TAG와 회원가입시 등록한 MemberTAG 값(value) 비교.
    /*  for(var i=0; i<ProjectTagList.length;i++){
         for(var j=0; j < MemberTagList.length; j++){
        	 if(ProjectTagList[i].value == MemberTagList[j].value){
        		// 같으면 체크. 그리고 다음 반복문.
        		 ProjectTagList[i].checked = true;
        		 break;
        	 }
         }
       }  */
  	 // 옵션 값 중에 기존회원의 값과 SELECT 박스 안의 옵션값 일치여부를 확인
     // 같으면 그 값으로 선택되게 하고 selected 속성 true.
     for(var i=0; i<OPTIONTEL1.length;i++){
       val = OPTIONTEL1[i].value;
       if(TEL1 == val){
    	   OPTIONTEL1[i].selected= true;
        break;
       }
     }  
  	 // 옵션 값 중에 기존회원의 값과 SELECT 박스 안의 옵션값 일치여부를 확인
     // 같으면 그 값으로 선택되게 하고 selected 속성 true.
     for(var i=0; i<OPTIONHP1.length;i++){
         val = OPTIONHP1[i].value;
         if(HP1 == val){
        	 OPTIONHP1[i].selected= true;
          break;
         }
       } 
     // 옵션 값 중에 기존회원의 값과 SELECT 박스 안의 옵션값 일치여부를 확인
     // 같으면 그 값으로 선택되게 하고 selected 속성 true.
     for(var i=0; i<OPTIONEMAIL2.length;i++){
         val = OPTIONEMAIL2[i].value;
         if(EMAIL2 == val){
        	 OPTIONEMAIL2[i].selected= true;
          break;
         }
       }     
   }


function fn_modify_member_info(obj){
	var a = $("input:password[name=MEMBER_PW]").val();
	if (a == ''){
	      alert('비밀번호를 입력 해주세요');
	         return false;
	 }
	 obj.action="${contextPath}/mypage/modifyMyInfo.do";
	 obj.submit();
}

 function fn_modify_tag(){
	var select;
	var menu_tag_third = document.getElementById('menu_tag_third').value;
	
	alert(menu_tag_third);

}
 
function addTag(TAG_THIRD){
	var MEMBER_ID = $('#MEMBER_ID').val()
	
	 
	alert(TAG_THIRD);

	 $.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/mypage/addTag.do",
			dataType:"text",
			data : {
				MEMBER_ID:MEMBER_ID,
				TAG_THIRD:TAG_THIRD,
			},
			success : function(data,textStatus) {
				 if(data.trim() =='true'){
					alert("회원 정보를 수정했습니다.");
				}else if(data.trim()=='false'){
					alert("이미 등록 된 태그 입니다.");	
				} 
			},
			error : function(data,textStatus) {
				alert("에러가 발생했습니다."+data);
			},
			complete : function(data, textStatus) {
				
			}
		}); //end ajax
		history.go(0);
	}
 
</script>
</head>

<body>
	<h3>내 상세 정보</h3>
<form name="frm_mod_member" onsubmit="fn_modify_member_info(frm_mod_member)">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
						<input id="MEMBER_ID" name="MEMBER_ID" type="text" size="20" value="${member.MEMBER_ID}"  disabled/>
					</td>
					 <td>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td>
					  <input name="MEMBER_PW" type="password" size="20" value="${member.MEMBER_PW }" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이름</td>
					<td>
					  <input name="MEMBER_NAME" type="text" size="20" value="${member.MEMBER_NAME }"  disabled />
					 </td>
					 <td>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">성별</td>
					<td>
					  <c:choose >
					    <c:when test="${member.MEMBER_GENDER =='M' }">
					      <input type="radio" name="MEMBER_GENDER" value="F" />
						  여성 <span style="padding-left:30px"></span>
					   <input type="radio" name="MEMBER_GENDER" value="M" checked />남성
					    </c:when>
					    <c:otherwise>
					      <input type="radio" name="MEMBER_GENDER" value="F"  checked />
						   여성 <span style="padding-left:30px"></span>
					      <input type="radio" name="MEMBER_GENDER" value="M"  />남성
					   </c:otherwise>
					   </c:choose>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">법정생년월일</td>
					<td>
					   <select name="MEMBER_BIRTH_Y">
					     <c:forEach var="i" begin="1" end="100">
					       <c:choose>
					         <c:when test="${member.MEMBER_BIRTH_Y==1920+i }">
							   <option value="${ 1920+i}" selected>${ 1920+i} </option>
							</c:when>
							<c:otherwise>
							  <option value="${ 1920+i}" >${ 1920+i} </option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>년 
					<select name="MEMBER_BIRTH_M" >
						<c:forEach var="i" begin="1" end="12">
					       <c:choose>
					         <c:when test="${member.MEMBER_BIRTH_M==i }">
							   <option value="${i }" selected>${i }</option>
							</c:when>
							<c:otherwise>
							  <option value="${i }">${i }</option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>월 
					
					<select name="MEMBER_BIRTH_D">
							<c:forEach var="i" begin="1" end="31">
					       <c:choose>
					         <c:when test="${member.MEMBER_BIRTH_D==i }">
							   <option value="${i }" selected>${i }</option>
							</c:when>
							<c:otherwise>
							  <option value="${i }">${i }</option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>일 <span style="padding-left:50px"></span>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">전화번호</td>
					<td>
					    <select  name="TEL1" id="TEL1"  >
							<option value="00">없음</option>
							<option value="02">02</option>
							<option value="031">031</option>
							<option value="032">032</option>
							<option value="033">033</option>
							<option value="041">041</option>
							<option value="042">042</option>
							<option value="043">043</option>
							<option value="044">044</option>
							<option value="051">051</option>
							<option value="052">052</option>
							<option value="053">053</option>
							<option value="054">054</option>
							<option value="055">055</option>
							<option value="061">061</option>
							<option value="062">062</option>
							<option value="063">063</option>
							<option value="064">064</option>
							<option value="0502">0502</option>
							<option value="0503">0503</option>
							<option value="0505">0505</option>
							<option value="0506">0506</option>
							<option value="0507">0507</option>
							<option value="0508">0508</option>
							<option value="070">070</option>
					</select> 
					    - <input type="text" size=4  name="TEL2" value="${member.TEL2 }"> 
					    - <input type="text" size=4  name="TEL3" value="${member.TEL3 }">
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">휴대폰번호</td>
					<td>
					   <select  name="HP1" id="HP1">
							<option>없음</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> 
					 - <input type="text" name="HP2" size=4 value="${member.HP2 }"> 
					 - <input type="text"name="HP3"  size=4 value="${member.HP3 }"><br> <br>
					 <c:choose> 
					   <c:when test="${member.SMSSTS_YN=='Y' }">
					     <input type="checkbox"  name="SMSSTS_YN" value="Y" checked /> 홈페이지에서 발송하는 SMS 소식을 수신합니다.
						</c:when>
						<c:otherwise>
						  <input type="checkbox"  name="SMSSTS_YN" value="N"  /> 홈페이지에서 발송하는 SMS 소식을 수신합니다.
						</c:otherwise>
					 </c:choose>	
				    </td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이메일<br>(e-mail)</td>
					<td>
					   <input size="10px"   type="text" id="EMAIL1" name="EMAIL1" value="${member.EMAIL1 }" /> @
					   <input size="10px"  type="text" id="EMAIL2" name="EMAIL2" value = "${member.EMAIL2}" readonly /> 
						   <select onchange="Check_Email()" id="selectEmail" name="EMAIL3">
						    <option value="없음">없음</option>
							<option value="1">직접입력</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="naver.com">naver.com</option>
							<option value="yahoo.co.kr">yahoo.co.kr</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="paran.com">paran.com</option>
							<option value="nate.com">nate.com</option>
							<option value="google.com">google.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="empal.com">empal.com</option>
							<option value="korea.com">korea.com</option>
							<option value="freechal.com">freechal.com</option>
					</select><Br><br> 
					<c:choose> 
					   <c:when test="${member.EMAILSTS_YN=='Y' }">
					     <input type="checkbox" name="EMAILSTS_YN"  value="Y" checked /> 홈페이지에서 발송하는 e-mail을 수신합니다.
						</c:when>
						<c:otherwise>
						  <input type="checkbox" name="EMAILSTS_YN"  value="N"  /> 홈페이지에서 발송하는 e-mail을 수신합니다.
						</c:otherwise>
					 </c:choose>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">주소</td>
					<td>
					   <input type="text" id="ZIPCODE" name="ZIPCODE" size=5 value="${member.ZIPCODE }" > <a href="javascript:execDaumPostcode()">우편번호검색</a>
					  <br>
					  <p> 
					   지번 주소:<br><input type="text" id="ROADADDRESS"  name="ROADADDRESS" size="50" value="${member.ROADADDRESS }"><br><br>
					  도로명 주소: <input type="text" id="JIBUNADDRESS" name="JIBUNADDRESS" size="50" value="${member.JIBUNADDRESS }"><br><br>
					  나머지 주소: <input type="text"  name="NAMUJIADDRESS" size="50" value="${member.NAMUJIADDRESS }" />
					  <span id="guide" style="color:#999"></span>
					   </p>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="clear">
		<br><br>
		<table align=center>
		<tr>
			<td >
				<input type="hidden" name="command"  value="modify_my_info" />
				<input type="submit" value="수정하기"  /> 
				<input name="btn_back_member" type="button" value="돌아가기"
				 	 onClick="location.href='${contextPath}/member/listMembers.do'">
			</td>
		</tr>
	</table>
	</div>
	<input  type="hidden" name="H_TEL1" value="${member.TEL1}" />
	<input  type="hidden" name="H_HP1" value="${member.HP1}" />		
	
<form name="frmTag">
	<table border="1"  align="center"  width="80%">
	   	<tr align="center"   bgcolor="lightgreen"><td colspan="5">해쉬태그</td></tr>
	   	<tr>
		   	<td align="center">
		   	 	대분류
		   	</td>
		   	<td align="center">
		   		중분류
		   	</td>
		   	<td align="center">
		   		소분류
		   	</td>
		   	<td align="center">
		   		버튼
		   	</td>
	   	</tr>
   	<c:forEach var="ProjectTagList" items="${ProjectTagList}" >
	   	<tr align="center">
		   	<td>${ProjectTagList.TAG_FIRST}</td>
		   	<td>${ProjectTagList.TAG_SECOND}</td>
		   	<td>${ProjectTagList.TAG_THIRD}</td>
		   	<td>
		   		<input type="button"  value="추가하기"  onClick="addTag('${ProjectTagList.TAG_THIRD}')"  /> 
		   	</td>
	   	</tr>
  	 </c:forEach>
   </table>
</form>

<form name="frmMyTag" id="tag1"> 
   <table  border="1"  align="center"  width="80%">
	   <tr align="center" bgcolor="lightgreen">
	 	  <td colspan="2">MyTag</td>
	   </tr>
   <c:forEach var="MemberTagList" items="${MemberTagList}">
	   <tr align="center">
		   <td>${MemberTagList.TAG}</td>
		   <td>
		   	 <a href="${contextPath}/mypage/removeTag.do?MEMBER_ID=${member.MEMBER_ID}&MEMBER_TAG=${MemberTagList.TAG}">삭제하기</a>
		   </td>
	   </tr>
   </c:forEach>
   </table>
</form>
	
	
	
	<%-- <tr class="dot_line">
		<td text-align="center"><h3>관심분야</h3></td>
		<c:forEach var="ProjectTagList" items="${ProjectTagList}">
			<tr>
				<td>${ProjectTagList.TAG_THIRD}</td>
				<td><input type="checkbox" id="ProjectTagList" name="ProjectTagList" value="${ProjectTagList.TAG_THIRD}"></td>
			</tr>
		</c:forEach>
	</tr>
<br>
   <c:forEach var="MemberTagList" items="${MemberTagList}">
	   <td >
	   		<input type="hidden" id="MemberTagList" name="MemberTagList" value="${MemberTagList.TAG}">
	   </td>
   </c:forEach> --%>
   
<!-- </form> -->	
</body>
</html>
