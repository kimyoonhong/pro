<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<!DOCTYPE html>
<html>
<head>
<style type="text/css">
/* Reset */
 * {outline:0 !important;}

#ipt_keyword{
	height: 50px;
}
#ipt_keyword:active{
	border-style: none;
}
    #tag{
    	visibility: hidden;
        z-index: 1;
        position: absolute;
        left: 0px;
        top: 54px;
        width: 700px;
        background-color: rgba( 255, 255, 255, 1 );
    }
     .tagtable{
        width: 700px;
        text-align: center;
    }

 ul, ol, li{list-style:none;}
  #header .hide {
            display: none;
        }
        #header html { font-size: 20px;}
        /* body {font-size: 1rem;} */

        /* $blue: #5f98fc; */
        /* $white: #fff; */
        /* $yellow: #fdfa70; */

       #header header {
            margin: 8px;
            position: relative;
            padding: 0 100px 0 ; 
             }
             
      #header  header::before {
            position:absolute;
            top:0;bottom:25px;left:0;z-index:-1;
            width:100%;background:#5f98fc;content:""
            }
            
      #header  header h1 { 
            float:left; 
            padding:40px 0 0 0  ;
           }
           
      #header  header h1 a {
            display:block;
            font-size: 40px;
            color: #fff;  
            text-decoration:none;
            /* height: 50px; */
            /* width: 298px;  */
            /* padding: 50px 0 0 ; */
            /* background: 
            url(./images/h1_logo.png)
            no-repeat 0 0 / 100% auto; */
                }
      #header  header .info {
            float:right;  
            padding: 30px 0 0 ; 
        }
      #header header ul,li{
      list-style:none;}  
      #header  header .info a {
            border-radius: 20px;
            vertical-align: middle;
            font-size: 15px;
            font-style: bold;
            text-align: center;
            border:2px solid #fff;
            color:#000;text-decoration:none;
        }

      #header  header .info a:hover {
            color: black;
            text-decoration: underline;
        }

     #header   header .info .mypage,.menu {
            display: inline-block;
            padding: 10px 0 10px 0;
            margin: 40px 0 0 20px ;
            width: 100px; 
            color: #fff;         
        }
       
       
        /* --------------네비게이션------------ */
     #header   header nav ul {
            display: flex;
            
            justify-content: space-between;
        }

     #header   header nav ul li {
            
        }

      #header  header nav ul li a{
            padding: 20px;
            display: block;
            position: relative;
            height: 40px; line-height: 40px;
            font-size: 20px;
            color: #fff;
        }
       #header header nav ul li a:hover {
            color:yellow
        }
       #header header nav ul li a:before{
            content: ""; 
            position:absolute;
            left:0; bottom: 0; width: 0;
            height: 5px;
            background: yellow;
            transition: all .5s;
        }
      #header  header nav ul li a:hover:before{
            width: 100%;
        }

       /* ---------------- 헤더  검색 -------------- */
      #header header #search {
           box-sizing: border-box;
           padding: 0 100px 0;
       }

      #header header #search .main_search::before {
           position: absolute;
           bottom: 0;
           left: 0;
           width: 1px;
           height: 1px;
           content: "";
       }

      #header header .main_search {
           position: relative;
           z-index: 2;
           margin: 0 auto;
           width: 800px;
           height: 53px;
           box-shadow: 0 6px 10px rgba(0, 0, 0, 0.11);
       }

      #header header .main_search .option {
           position: absolute;
           border:none;
           top: 0;
           z-index: 1;
           height: 53px;
           box-sizing: border-box;
           color: #666;
           font-size: 15px;
           letter-spacing: -1px;
           text-align: left;
           background: #fff;
       }

      #header .main_search .option:before {
            display: block;
            position: absolute;
            top: 17px;
            left: 22px;
            z-index: 4;
            width: 20px;
            height: 20px;
            content: "";
        }

      #header header .main_search .option.option_category {
           left:1px; width:350px;
           border-right: solid 1px gray;
        }
      #header header .main_search .option.option_category input[type=button] {
      	   border:none; font-family:'Noto Sans KR', sans-serif;
           background: #fff; height: 100%; width:100%; color: gray; 
           font-size: 20px; border-right: solid 1px gray;
        }

      #header header .main_search .option.option_keyword {left:350px; width:350px; }
      #header header .main_search .option.option_keyword input[type=text] 
      {
      	border:none; font-family:'Noto Sans KR', sans-serif;
      	width:100%; height:100%; font-size:20px; color:#fff;  
		text-indent:20px; vertical-align:middle;
      	background: #fff;color: gray; text-align: center;
      	font-size:20px;
      }
       /* header .main_search .option_keyword:before {background-position: 1px -299px;} */
      
      #header header .main_search .option.option_search {left:700px; width:100px;}
      #header header .main_search .option.option_search input[type=submit] {
      border:none; font-family:'Noto Sans KR', sans-serif;
      color:#727272;
      background: gray; height: 100%; width:100%; color: #fff;font-size: 20px;}
       
       /* 검색 패널 */

      #header .panel .details { 
           display : none; 
       }

      #header .main_search .panel {position:absolute;top:53px;box-sizing:border-box;background:#fff;box-shadow:0 3px 6px 0 rgba(0,0,0,0.2);}
      #header .main_search .option.option_category .panel {width:700px;}
      #header. .main_search .panel:before {display:block; position:absolute;top:-1px;width:100%;height:1px;background:#eaeaea;content:"";}
       
      #header .panel .details {height:200px;}
      #header .panel .details:after {display:table;clear:both;content:"";table-layout:fixed}
      #header .panel .details .wrap_scroll {float:left;position:relative;height:231px;box-sizing:border-box}

       /* 대분류 */
       #header .wrap_scroll .overview {position:absolute;top:0;left:0;margin:0;padding:0;width:100%;list-style:none}
        /* 소분류 */
       #header .panel .filters {width:350px;border-left:1px solid #e5e5e5}
       #header .panel .filters .list_check{padding-top: 10px;}
       #header .panel .filters .overview {padding:48px 0 9px}
       #header .panel .filters ul:after {display:table;clear:both;content:"";table-layout:fixed}
       #header .panel .filters li {display:block;float:left;padding:7px 0 7px 20px;width:174px;box-sizing:border-box;line-height:18px}
       #header .panel .filters li label {overflow:hidden;max-width:100%;color:#888;text-overflow:ellipsis;white-space:nowrap;cursor:pointer}
       #header .panel .filters li label span {color:#222}
       #header .panel .filters li.select_all label {font-weight:bold}
       /* 대 중 분류 공통 */
       #header .wrap_scroll .viewport {overflow:hidden;position:relative;z-index:1;width:100%;height:100%}
       #header .panel .categories {width:174px}
       #header .panel .categories+.categories {border-left:1px solid #e5e5e5}
       #header .panel .categories .overview {padding:10px 0 9px}
       #header .panel .categories li {padding:0 0 1px;box-sizing:border-box}
       #header .panel .categories li button {padding:8px 0 10px 20px;width:100%;height:35px;color:#888;font-size:13px;letter-spacing:-1px;line-height:17px;text-align:left}
       #header .panel .categories li button span {margin-right:4px;color:#222}
       #header .panel .categories li:hover button,
       #header .panel .categories li.show button {position:relative;background:#f8faff}
       #header .panel .categories li button:after {display:block;position:absolute;top:25px;right:15px;width:6px;height:11px;background:url(//www.saraminimage.co.kr/sri/main/img_main_spr_190910.png) no-repeat -700px -300px;content:""}
       #header .panel .categories li:hover button span,
       #header .panel .categories li.show button span,
       #header .panel .categories li.selected button span {color:#566feb;font-weight:bold}
     


header a{color:#000;text-decoration:none;}

.clear {clear:both;}
.clear:after { content:""; display:block; clear:both;}

</style>
  <meta charset="UTF-8">
<title>헤더</title>
  <script>
  function fn_mypage(isLogOn,myProjcet,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=myProjcet;
	  }else{
	    alert("로그인 후 사용 가능합니다 ")
	    event.preventDefault()
	    window.location.href=loginForm;
	    /* +'?action=/mypage/myProject.do' */
	  }
	}
  
  function fn_tag(){
		var a = document.getElementById("tag");
		if( a.style.visibility=="hidden"){
			a.style.visibility="visible";
		}else{
			a.style.visibility="hidden";
		}
		
		$.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/project/tagList.do",
			success : function() {
				//alert(data);
			},
			error : function(data, textStatus) {
				//alert("에러가 발생했습니다.");
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
				
			}
		}); //end ajax	

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
  </script>

</head>
<body>
<header class="border">
	<h1><a href="${contextPath}/project/main.do" >CanYouJoinUs</a></h1>
    <div class="info">
    <!-- isLogOn 속성 값을 체크하여 로그인 상태 시 로그아웃이 표시 되게 한다. -->
        <c:choose>
            <c:when test="${isLogOn == true  && member!= null}">
            <!-- 컨트롤러에서 member변수에 VO객체를 세션으로 보내준다. -->
            <a href="${contextPath}/member/logout.do" class="mypage">로그아웃</a>
            <a href="${contextPath}/mypage/myDetailInfo.do" class="mypage">회원정보수정</a>
            <a href="${contextPath}/project/projectadd.do" class="mypage">프로젝트 작성</a>
            <!-- 로그아웃 링크를 클릭하면 로그인을 표시. -->
            </c:when>
            <c:when test="${isLogOn == true  && admin!= null}">
            <a class="mypage">${admin.ADMIN_NAME}모드</a>
            <a href="${contextPath}/admin/logout.do" class="mypage">로그아웃</a>
            </c:when>
            <c:otherwise>
                <a href="${contextPath}/member/loginForm.do" class="mypage">로그인</a>
                <a href="${contextPath}/member/memberForm.do" class="mypage">회원가입</a>
            <!-- 로그인 링크를 클릭하면 로그인창 요청. -->
            </c:otherwise>
        </c:choose>   
    </div>
    <c:choose>
    <c:when test="${isLogOn == true  && admin!= null}">
    <nav class="clear">
        <ul>
            <li><a href="${contextPath}/admin/listnotice.do">공지사항 관리</a></li>
            <li><a href="${contextPath}/project/listprojects.do">프로젝트 관리</a></li>
            <li><a href="${contextPath}/qna/listArticles.do">건의사항 관리</a></li>
            <li><a href="${contextPath}/member/listMembers.do">회원 관리</a></li>
            <li><a href="${contextPath}/admin/admintag.do">테그 관리</a></li>
        </ul>
    </nav>
     </c:when>
     <c:otherwise>
    <nav class="clear">
        <ul>
            <li><a href="${contextPath}/admin/listnotice.do">공지사항</a></li>
            <li><a href="${contextPath}/qna/listArticles.do">자유게시판</a></li>
            <li><a href="${contextPath}/project/listprojects.do">프로젝트</a></li>
            <li><a href="${contextPath}/mypage/myProject.do" onClick="fn_mypage('${isLogOn}','${contextPath}/mypage/myProject.do','${contextPath}/member/loginForm.do')">마이페이지</a></li>
        </ul>
    </nav>
    </c:otherwise>
    </c:choose>
    <div id="search">
        <form name="frmSearch" action="${contextPath}/project/searchprojects.do" method = "posts">
            <div class="main_search">
                <div class="option option_category">
                    <label for="ipt_category" class="placeholder" style="color: rgb(72,118,239)"></label>
                    <input type="button" style="font-size:24px;" id="itp_category" class="key" maxlength="40%" onClick="fn_tag()" value="카테고리 선택">
                     <div id="tag">
            <table class="tagtable">
           <%--  <tr align="center"   bgcolor="lightgreen">
			<td>대분류</td>
			<td>중분류</td>
			<td>소분류</td>
			</tr>
           <c:forEach var="tag" items="${projectTagList}">
			<tr align="center">
			<td>${tag.TAG_FIRST}</td>
			<td>${tag.TAG_SECOND}</td>
			<td>${tag.TAG_THIRD}</td>
			<td><input type="checkbox" id="selecttag" name="selecttag" onClick="count_ck(this)" value="${tag.TAG_THIRD}"></td>
			</tr>
			</c:forEach> --%>
			<tr align="center" bgcolor="lightgreen">
			<td>대분류</td>
			<td>중분류</td>
			<td>소분류</td>
			</tr>
           	<input type="hidden" id="selecttag" name="selecttag" onclick="count_ck(this)" value="null">
           
			<tr align="center">
			<td>IT</td>
			<td>웹</td>
			<td>프론트엔드</td>
			<td><input type="checkbox" id="selecttag" name="selecttag" onclick="count_ck(this)" value="프론트엔드"></td>
			</tr>
			
			<tr align="center">
			<td>IT</td>
			<td>웹</td>
			<td>백엔드</td>
			<td><input type="checkbox" id="selecttag" name="selecttag" onclick="count_ck(this)" value="백엔드"></td>
			</tr>
			
			<tr align="center">
			<td>IT</td>
			<td>IOT</td>
			<td>아두이노</td>
			<td><input type="checkbox" id="selecttag" name="selecttag" onclick="count_ck(this)" value="아두이노"></td>
			</tr>
			
			<tr align="center">
			<td>IT</td>
			<td>IOT</td>
			<td>라즈베리</td>
			<td><input type="checkbox" id="selecttag" name="selecttag" onclick="count_ck(this)" value="라즈베리"></td>
			</tr>
            </table>
       			</div>    
              
                </div>
                <div class="option option_keyword ">
                    <label for="ipt_keyword" class="placeholder" style="color: rgb(72,118,239)"></label>
                    <input type="text" style="font-size:24px;"  name="searchWord" id="ipt_keyword" class="key" maxlength="40%" placeholder="제목을 입력하세요.">
                </div>
                <div class="option option_search">
                    <label for="ipt_search" class="placeholder" style="color: rgb(72,118,239)"></label>
                    <input type="submit" id="ipt_search" name="search"  class="key" maxlength="40%" value="검 색" onClick="fn_ck()">
                   
                </div>
            </div>
        </form>
    </div>
</header>
    
</body>
</html>