<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>QnA게시글목록</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <style type="text/css">
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
		 	
  #pageNum li {
  	list-style: none; 
  	float: left;
  	marign: 150px; 
  	padding: 6px;
  	
  	}
  
  #contents_tb tr:hover {
	background-color: #0066ff;
		}
		
  .write_button {
    background-color: #475d9f;
    border: 1px solid #323f6b;
    color: #ffffff;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 15px;
	}	
  </style>

  
</head>
<script>
	function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=articleForm;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=loginForm+'?action=/qna/articleForm.do';
	  }
	}
</script>
<body>

<table align="center" border="1"  width="80%"  >
  <tr height="10" align="center"  bgcolor="#5f98fc">
     <td >글번호</td>
     <td >QNA태그</td> 
     <td >작성자</td>             
     <td >제목</td>
     <td >작성일</td>
  </tr>
<c:choose>
  <c:when test="${qna_articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${qna_articlesList !=null }" >
    <c:forEach  var="article" items="${qna_articlesList }" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${article.QNA_ARTICLENO}</td>
	<td width="10%">${article.QNA_TAG}</td>
	<td width="10%">${article.MEMBER_ID}</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
        <c:choose>
	      <c:when test='${article.level > 1 }'>  
	         <c:forEach begin="1" end="${article.level }" step="1">
	              <span style="padding-left:20px"></span>    
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="${contextPath}/qna/viewArticle.do?QNA_ARTICLENO=${article.QNA_ARTICLENO}">${article.QNA_TITLE}</a>
	          </c:when>
	          <c:otherwise>
	            <a class='cls1' href="${contextPath}/qna/viewArticle.do?QNA_ARTICLENO=${article.QNA_ARTICLENO}">${article.QNA_TITLE }</a>
	          </c:otherwise>
	        </c:choose>
	  </td>
	  </td>
	  <td  width="10%">${article.writeDate}</td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
<!-- 
<div id="pageNum">
  <ul>
    <c:if test="${pageMaker.prev}">
    	<li><a href="listArticles.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li><a href="listArticles.do${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="listArticles.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>
 -->
<a  class="cls1"  href="javascript:fn_articleForm('${isLogOn}','${contextPath}/qna/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><button class="write_button" input type="submit" value="글쓰기" >글쓰기</button></a>

<div id="reply">
  <ul class="replyList">
    <c:forEach items="${replyList}" var="replyList">
      <li>
        <p>
        글번호 : ${replyList.QNA_ARTICLENO} <br>
        작성자 : ${replyList.writer}<br />
        작성 날짜 :  <fmt:formatDate value="${replyList.regdate}" pattern="yyyy-MM-dd" />
        </p>

        <p>${replyList.content}</p>
      </li>
    </c:forEach>   
  </ul>
</div>
</body>
</html>