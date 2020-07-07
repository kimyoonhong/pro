<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
/* 이메일 체크 */
//이메일 입력방식 선택
function Check_Email() {
   var EMAIL2 = $("#EMAIL2").val(); // input 박스  값 
   var selectEmail = $("#selectEmail").val();// select박스 선택 값
        
   // 직접 입력 선택했을 경우
   if (selectEmail == '1'){
	   $('#EMAIL2').prop("value",''); // input 박스 초기화
	   $('#EMAIL2').prop("readonly",false);// input 박스 readonly 속성 비활성화
   }else {
   // 이메일 선택했을경우   
      $('#EMAIL2').prop("value",selectEmail); // 선택 값 input박스로 전달
      $('#EMAIL2').prop("readonly",true); // input 박스 readonly 속성 활성화
      alert(selectEmail);
      
   }
}
</script>



<script>
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

function fn_overlapped(){
	 var _MEMBER_ID=$("#_MEMBER_ID").val();
	
	    if(_MEMBER_ID==''){
	   	 alert("ID를 입력하세요");
	   	 return;
	    }
	    $.ajax({
	       type:"post",
	       async:false,  
	       url:"${contextPath}/member/overlapped.do",
	       dataType:"text",
	       data: {MEMBER_ID:_MEMBER_ID},
	       success:function (data,textStatus){
	          if(data=='false'){
	       	    alert("사용할  수 있는 ID입니다.");
	       	    $('#btnOverlapped').prop("disabled", true);
	       	    $('#_MEMBER_ID').prop("disabled", true);
	       	    $('#MEMBER_ID').val(_MEMBER_ID);
	          }else{
	        	  alert("사용할 수 없는 ID입니다.");
	          }
	       },
	       error:function(data,textStatus){
	          alert("에러가 발생했습니다.");
	       },
	       complete:function(data,textStatus){
	          //alert("작업을완료 했습니다");
	       }
	    });  //end ajax	 
	 }	
</script>
</head>
<body>
	<h3>필수입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
					  <input type="text" name="_MEMBER_ID"  id="_MEMBER_ID"  size="20" />
					  <input type="hidden" name="MEMBER_ID"  id="MEMBER_ID" />
					  
					  <input type="button"  id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td><input name="MEMBER_PW" type="password" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이름</td>
					<td><input name="MEMBER_NAME" type="text" size="20" /></td>
					
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">성별</td>
					<td><input type="radio" name="MEMBER_GENDER" value="F" />
						여성<span style="padding-left:120px"></span>
						 <input type="radio" name="MEMBER_GENDER" value="M" checked />남성
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">법정생년월일</td>
					<td>
					<select name="MEMBER_BIRTH_Y">
					 
					     <c:forEach var="year" begin="1" end="100">
					       <c:choose>
					         <c:when test="${year==75}">
							   <option value="${ 1920+year}" selected>${ 1920+year} </option>
							</c:when>
							<c:otherwise>
							  <option value="${ 1920+year}" >${ 1920+year} </option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach> 
							
					</select>년 
					 <select name="MEMBER_BIRTH_M" >
					   <c:forEach var="month" begin="1" end="12">
					       <c:choose>
					         <c:when test="${month==1 }">
							   <option value="${month }" selected>${month }</option>
							</c:when>
							<c:otherwise>
							  <option value="${month }">${month}</option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>월  
					<select name="MEMBER_BIRTH_D">
							<c:forEach var="day" begin="1" end="31">
					       <c:choose>
					         <c:when test="${day==7 }">
							   <option value="${day}" selected>${day}</option>
							</c:when>
							<c:otherwise>
							  <option value="${day}">${day}</option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>일 <span style="padding-left:50px"></span>
					  <!-- <input type="radio" name="MEMBER_BIRTH_GN" value="2" checked />양력
						 <span style="padding-left:50px"></span>
						<input type="radio"  name="MEMBER_BIRTH_GN" value="1" />음력 -->
				  </td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">전화번호</td>
					<td><select  name="TEL1">
							<option>없음</option>
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
					   </select> - <input  size="10px" type="text" name="TEL2"> - <input size="10px"  type="text" name="TEL3">
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">휴대폰번호</td>
					<td><select  name="HP1">
							<option>없음</option>
							<option selected value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> - <input size="10px"  type="text" name="HP2"> - <input size="10px"  type="text"name="HP3"><br> <br> 
					 <input type="checkbox"	name="SMSSTS_YN" value="Y" checked /> 홈페이지에서 발송하는 SMS 소식을 수신합니다.</td> 
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이메일<br>(e-mail)</td>
					<td><input size="10px"   type="text" name="EMAIL1" /> @ 
						<input  size="10px"  type="text" id="EMAIL2" name="EMAIL2" value = "선택하세요" readonly /> 
						   <select onchange="Check_Email()" id="selectEmail" name="EMAIL3">
									<option value="선택하세요" selected>선택하세요</option>
									<option value="1">직접입력</option>
									<option value="naver.com">네이버</option>
									<option value="daum.net">다음</option>
									<option value="yahoo.co.kr">야후</option>
									<option value="hotmail.com">핫메일</option>
									<option value="paran.com">파란</option>
									<option value="nate.com">네이트</option>
									<option value="google.com">구글</option>
									<option value="gmail.com">G메일</option>
									<option value="empal.com">empal닷컴</option>
									<option value="korea.com">korea닷컴</option>
									<option value="freechal.com">freechal닷컴</option>
							</select><br> <br>   
							<input type="checkbox" name="EMAILSTS_YN" value="Y" checked /> 홈페이지에서 발송하는 e-mail을 수신합니다.</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">주소</td>
					<td>
					   <input type="text" id="ZIPCODE" name="ZIPCODE" size="10" > <a href="javascript:execDaumPostcode()">우편번호검색</a>
					  <br>
					  <p> 
					   도로명 주소:<br><input type="text" id="ROADADDRESS"  name="ROADADDRESS" size="50"><br><br>
					  지번 주소: <input type="text" id="JIBUNADDRESS" name="JIBUNADDRESS" size="50"><br><br>
					  나머지 주소: <input type="text"  name="NAMUJIADDRESS" size="50" />
					 <span id="guide" style="color:#999"></span>
					   </p>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">자기소개</td>
					<td>
						<input type="text" id="MEMBER_CONTENT" name="MEMBER_CONTENT">
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">전공</td>
					<td>
						<input type="text" id="MEMBER_JOB" name="MEMBER_JOB">
					</td>
				</tr>
				<tr class="dot_line">
					<td text-align="center"><h3>관심분야</h3></td>
					<c:forEach var="tag" items="${projectTagList}">
					<tr>
						<td>${tag.TAG_THIRD}</td>
						<td><input type="checkbox" id="tagVO" name="tagVO" value="${tag.TAG_THIRD}"></td>
					</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="clear">
		<br><br>
		<table align=center>
		<tr >
			<td >
				<input type="submit"  value="회원 가입">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
	</table>
	</div>
</form>	
</body>
</html>