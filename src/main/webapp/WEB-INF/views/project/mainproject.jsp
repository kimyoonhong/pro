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
<style>
       @import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');
      @import url('https://fonts.googleapis.com/css2?family=Jua&family=Poor+Story&display=swap');
      @import url('https://fonts.googleapis.com/css2?family=Jua&family=Poor+Story&family=Yeon+Sung&display=swap');
        #main{
            margin: 10px;
            padding: 20px;
            height: 200px;
            width: 200px;
            border: 1px solid white;
            border-radius: 40px 15px 40px 15px;
            overflow: hidden;
          
        }
        #projcetList{
            margin: 10px;
            padding: 10px;
            height: 40px;
            /*border: solid 1px green;*/
            display: block;
            color : #6600ff;
            font-family: 'Yeon Sung', cursive;
        }
        #cont{
            margin: 0 auto;
            height: 100%;
            /*border: solid 1px red;*/

        }
        #TITTLE{
            padding: 1px;
            /*border: 1px solid teal;*/
            display:-webkit-box;
            -webkit-line-clamp:3;
            -webkit-box-orient:vertical;
            width:200px;
            height:42px;
            line-height:20px;
            overflow:hidden;
            text-overflow:ellipsis;
            font-family: 'Jua', sans-serif;
            font-size: 20px;
           
        } 
        #TITTLE a{
         color: royalblue;
        text-decoration: none;
        }
        
        #projcetList a{
        	font-size: 20px;   
        	color: blueviolet;                
            text-decoration: none;
        }
        #projectList span {
            margin: 1px;
            padding: 1px;
        }
        .main{
        
        width: 100px;
        height: 100px;
        display: inline-block; /*이부분에 성질을 inline-block로 바꿔줘서 가로배치를 해줬다.*/
        vertical-align: top; /*밑에 4px 여백을 없에는것*/
        background-color: #ccefff;
        
        }
        #DAY{
            width:200px;
            /*border: 1px solid red;*/
            display: inline-block;
            line-height: 5px;
            margin-top:10px;
            text-align: center;
        }
        
        #DAY p{
        font-size: 18px;
        font-family: 'Poor Story', cursive;
        }
        
        #projcetList a{
        font-size : 20px;
        }
        
        #TITTLE a{
        font-size : 24px;
        }
        #DAY P .endday{
        color : red;
        }
    </style>
</head>
<body>
    <div id="cont"> 
    <c:forEach var="project" items="${projectsList}" varStatus="a" >  
    <c:if test="${a.count<13 }">
    <div id="main" class="main">
        <div id="TITTLE" >
        <a href="${contextPath}/project/projectDetail.do?PROJECT_CODE=${project.PROJECT_CODE}">${project.PROJECT_TITTLE}</a>
        </div>
        <div id="projcetList">
        <c:forEach var="ALLprojecttagList" items="${ALLprojecttagList}" varStatus="r" >
        	<c:if test="${project.PROJECT_CODE ==ALLprojecttagList.PROJECT_CODE }">
            <a><span>#${ALLprojecttagList.PROJECT_TAG}</span></a>
          	</c:if>
        </c:forEach>
        </div>
        <div id="DAY" >
            <p>시작일 : ${project.PROJECT_STRDAY}</p>
            <p>마감일 : ${project.PROJECT_ENDDAY}</p>
        </div>
    </div>
    </c:if>
    </c:forEach>
    </div>
