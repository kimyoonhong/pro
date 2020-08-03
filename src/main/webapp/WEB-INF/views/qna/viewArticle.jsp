<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%-- 
<c:set var="article"  value="${articleMap.QNA_ARTICLENO}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />

 --%>
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
     
    .fixed_left{
	float:left;
		}
     
    .button {
    background-color: #475d9f;
    border: 1px solid #323f6b;
    color: #ffffff;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 15px;
		}
   .form_box {

    margin: 8px;
    border-radius: 4px;
    border: 1px solid #ddd;
    padding-left: 10px;
    padding-right: 10px;
   }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="${contextPath}/qna/listArticles.do";
	    obj.submit();
     }
 
	 function fn_enable(obj){
		 document.getElementById("QNA_TITLE").disabled=false;
		 document.getElementById("QNA_CONTENT").disabled=false;
		 //document.getElementById("IMAGEFILENAME").disabled=false; 
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/qna/modArticle.do";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,QNA_ARTICLENO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","QNA_ARTICLENO");
	     articleNOInput.setAttribute("value", QNA_ARTICLENO);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 
	 }
	 /*
	 function fn_reply_form(url, QNA_PARENTNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var parentNOInput = document.createElement("input");
	     parentNOInput.setAttribute("type","hidden");
	     parentNOInput.setAttribute("name","QNA_PARENTNO");
	     parentNOInput.setAttribute("value", QNA_PARENTNO);
		 
	     form.appendChild(parentNOInput);
	     document.body.appendChild(form);
		 form.submit();
	 }
	 */
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }  
	 
	 $(".replyWriteBtn").on("click", function(){
		  var formObj = $("form[name='replyForm']");
		  formObj.attr("action", "/qna/replyWrite.do");
		  formObj.submit();	
		});
	 
 </script>
</head>
<body>

  <form class="form_box" name="frmArticle" method="post"  action="${contextPath}"  enctype="multipart/form-data">
  <table  border=0  align="center">
  <tr>
   <td width=150 align="left" bgcolor=#5f98fc>
      글번호
   </td>
   <td >
    <input class="fixed_left" type="text"  value="${article.QNA_ARTICLENO }"  disabled />
    <input type="hidden" name="QNA_ARTICLENO" value="${article.QNA_ARTICLENO}"  />
   </td>
  </tr>
  <tr>
    <td width="150" align="left" bgcolor="#5f98fc">
      작성자 아이디
   </td>
   <td >
    <input class="fixed_left" type=text value="${article.MEMBER_ID }" name="MEMBER_ID"  disabled />
   </td>
  </tr>
  <tr>
    <td width="150" align="left" bgcolor="#5f98fc">
      제목 
   </td>
   <td>
    <input class="fixed_left" type=text value="${article.QNA_TITLE }"  name="QNA_TITLE"  id="QNA_TITLE" disabled />
   </td>   
  </tr>
  <tr>
    <td width="150" align="left" bgcolor="#5f98fc">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="QNA_CONTENT"  id="QNA_CONTENT"  disabled />${article.QNA_CONTENT }</textarea>
   </td>  
  </tr>
 
 <%-- <c:if test="${not empty imageFileList && imageFileList!='null' }">
	  <c:forEach var="item" items="${imageFileList}" varStatus="status" >
		    <tr>
			    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
			      이미지${status.count }
			   </td>
			   <td>
			     <input  type= "hidden"   name="originalFileName" value="${item.imageFileName }" />
			    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="preview"  /><br>
			   </td>   
			  </tr>  
			  <tr>
			    <td>
			       <input  type="file"  name="imageFileName " id="i_imageFileName"  value="이미지변경" disabled= "true"   onchange="readURL(this);"   />
			    </td>
			 </tr>
		</c:forEach>
 </c:if> --%>
 	 
 	 <!-- 
  <c:choose> 
	  <c:when test="${not empty article.IMAGEFILENAME && article.IMAGEFILENAME!='null' }">
	   	<tr>
		    <td width="150" align="center" bgcolor="#66ccff"  rowspan="2">
		      이미지
		   </td>
		   <td>
		     <input  type= "hidden"   name="originalFileName" value="${article.IMAGEFILENAME }" />
		    <img src="${contextPath}/download.do?QNA_ARTICLENO=${article.QNA_ARTICLENO}&imageFileName=${article.IMAGEFILENAME}" id="preview"  /><br>
		   </td>   
		  </tr>  
		  <tr>
		    <td ></td>
		    <td>
		       <input  type="file"  name="IMAGEFILENAME " id="IMAGEFILENAME"   disabled   onchange="readURL(this);"   />
		    </td>
		    
		  </tr> 
		   
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
				    <td width="150" align="center" bgcolor="#66ccff"  rowspan="2">
				      이미지
				    </td>
				    <td>
				      <input  type= "hidden"   name="originalFileName" value="${article.IMAGEFILENAME }" />
				    </td>
				    
			    </tr>
			    <tr>
				    <td ></td>
				    <td>
				       <img id="preview"  /><br>
				       <input  type="file"  name="IMAGEFILENAME " id="IMAGEFILENAME"   disabled   onchange="readURL(this);"   />
				    </td>
			  </tr>
		 </c:otherwise>
	 </c:choose>
	  -->
  <tr>
	   <td width="150" align="left" bgcolor="#5f98fc">
	      등록일자
	   </td>
	   <td>
	    <input class="fixed_left" type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
	   </td>   
  </tr>
  <tr   id="tr_btn_modify"  align="center" >
	   <td colspan="2" >
	       <input type=button value="수정반영하기"  onClick="fn_modify_article(frmArticle)"  >
           <input type=button value="취소"  onClick="backToList(frmArticle)">
	   </td>   
  </tr>
    
  <tr  id="tr_btn" >
   <td colspan="2" align="center">
       <c:if test="${member.MEMBER_ID == article.MEMBER_ID }">
	      <input class="button" type=button value="수정하기" onClick="fn_enable(this.form)">
	      <input class="button" type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/qna/removeArticle.do', ${article.QNA_ARTICLENO})">
	    </c:if>
	    <input class="button" type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
	     <!-- <input class="button" type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/qna/replyForm.do', ${article.QNA_ARTICLENO})"> -->
   </td>
  </tr>
 </table>
 <!-- 댓글 
 <div id="reply">
  <ul class="replyList">
    <c:forEach items="${replyList}" var="replyList">
      <li>
        <p>
        작성자 : ${member.MEMBER_ID}<br />
        작성 날짜 :  <fmt:formatDate value="${replyList.regdate}" pattern="yyyy-MM-dd" />
        </p>

        <p>${replyList.content}</p>
      </li>
    </c:forEach>   
  </ul>
 
  <form name="replyForm" method="post">
  <input type="hidden" id="QNA_ARTICLENO" name="QNA_ARTICLENO" value="${read.QNA_ARTICLENO}" />
  --  
  <input type="hidden" id="page" name="page" value="${scri.page}"> 
  <input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
  <input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
  <input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
  --
  <div>
    <label for="MEMBER_ID">댓글 작성자</label><input type="text" id="MEMBER_ID" name="MEMBER_ID" value="${member.MEMBER_ID}" disabled />
    <br/>
    <label for="content">댓글 내용</label><input type="text" id="content" name="content" value="${content}" />
  </div>
  <div>
 	 <a href="${contextPath}/replyWrite.do">좀 되라</a>
 	 
 	 <button type="button" class="replyWriteBtn" >작성</button>
  </div>
	</form>
	-->
  </form>
</body>
</html>