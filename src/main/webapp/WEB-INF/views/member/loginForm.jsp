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
  <meta charset="UTF-8">
<title>로그인창</title>
<script src="jquery-3.4.1.js"></script>
		<style>
            @import url('https://fonts.googleapis.com/css?jamily=Noto+Sans+KR&display=swap');
            *{margin: 0; padding:0; box-sizing: border-box;}
    
            body {
           		position: relative;
                font-family: 'Noto Sans KR', sans-serif;
                display: flex;
                justify-content: center;
                align-items:center;
                background: url('https://lh3.googleusercontent.com/proxy/5183OQuL-5jtRLVdoGxtC4sx3zk0JbWW5arD6F0ZTUnh_hgO4I_H6Dk3it7wTbxlzTrQaIVqzAwcxUxQ-MywD3vT8uBbeY5A_GQ-u799dM7paQ9WnoX4cdDm495UuxiXqeW0oaM6BCADj3Ps_J1_7Ggv1t514EW0mMbfKcDBeK80YHDds1Dn239rT5mgteTyIh8937AKs38LHc_o7wpTs_7pdZyz6ljxnayae1aOCBkFIKdgm2zuRtH6qL6cp86dWqJY4VsnMynUJnJdHnHk_QSEf6UiHhZeJGdvTrB7Dikdwwd7cnHOzBOxbUA2O8XJNAUXFyY') no-repeat center;
                background-size: cover;
            }
            body::before {
                content:"";
                position: absolute; z-index: 5;
                top:0; right:0; bottom: 0; left:0;
                background-color: rgba(0,0,0,.7);
            }
            .login-form {
            position: relative; 
            z-index:6;
            border: solid 3px #fff; 
            padding: 50px ;
            margin: 0 500px 0 500px; 
            }
            .login-form h1 {
                font-size: 32px; color:#fff;
                text-align: center;
                margin-bottom: 60px;
            }
            .int-area {
                width: 100%; position:relative;
                margin-top: 20px;
                }
            .int-area:first-child {margin-top: 0;}
            .int-area input {
                width:100%;
                padding: 20px 10px 10px;
                background-color: transparent;
                border: none;
                border-bottom: 1px solid #999;
                font-size: 18px; color:#fff;
                outline:none;
            }
            .int-area label {
                position: absolute; left: 10px; top:15px;
                font-size: 18px; color:#999;
                transition: all .5s ease;
            }
            .int-area input:focus + label,
            .int-area input:valid + label {
                top:0;
                font-size: 13px; color: #166cea;
            }
            .btn-area{margin-top: 30px;}
            .btn-area input {
                width: 100%; height: 50px;
                margin-top: 20px;
                background: #166cea;
                color: #fff;
                font-size: 20px;
                border: none;
                border-radius: 25px;
            }
            .btn-area input:hover {
            	background: #f33;
            }
        </style>


<c:choose>
	<c:when test="${result=='loginFailed' }">
	  <script>
	    window.onload=function(){
	      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
	    }
	  </script>
	</c:when>
</c:choose>  
</head>

<body>
<section class="login-form">
            <h1>LOGIN</h1>
            <form  method="post"  action="${contextPath}/member/login.do">
                <div class="int-area">
                    <input type="text" name="MEMBER_ID" id="MEMBER_ID"
                     required> 
                    <label for="MEMBER_ID">ID</label>
                </div>
                <div class="int-area">
                    <input type="password" name="MEMBER_PW" id="MEMBER_PW"
                     required> 
                    <label for="MEMBER_PW">PASSWORD</label>
                </div>
                <div class="btn-area">
                    <input type="submit" value="로그인" >
                    <a href="${contextPath}/project/main.do"><input type="button" value="돌아가기" ></a>
                </div>
            </form>
        </section>
</body>
</html>
