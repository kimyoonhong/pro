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
<title>QnA작성</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
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
    obj.action="${contextPath}/qna/listArticles.do";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
  } 

</script>
<style>
.form{
	
    padding-left: 250px;
    padding-right: 250px;

    background-color: #ffffff;
}
.form_box {
    background-color: #5f98fc;
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
    padding: 3px;
    margin: 3px 0;
    font-size: 14px;
    width: 89%;
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
.text-left{
	float: left;
	color: white;
	}
 	
</style>
 <title>글쓰기창</title>
</head>
<body>
	
  <form class="form" name="articleForm" method="post"   action="${contextPath}/qna/addNewArticle.do"   enctype="multipart/form-data">
   
	<div class="form_box">
    <h3>게시글 작성</h3>
    <a class="text-left">작성자 : </a><input class="input_field" type="text" value="${member.MEMBER_NAME }" readonly/><br><br>
    <a class="text-left">분류 :</a><a class="out_field" type="text"><select  onchange="Check_Email()" id="dd" name="QNA_TAG"  >
	<c:forEach var="tag" items="${tagList}">
            <option value="${tag.QNA_TAG}">${tag.QNA_TAG}</option>
    </c:forEach>
    </select>
    </class></a><br><br>
    <a class="text-left">제목 : </a><input class="input_field" type="text" name="QNA_TITLE" placeholder="제목을 입력해주세요." ><br><br>
    <a class="text-left">내용 : </a><br><textarea class="textarea_field" name="QNA_CONTENT" placeholder="내용을 입력해주세요."></textarea><br>
    <button class="write_button" input type="submit" value="글쓰기" />글쓰기</button>
	<button class="back_button" input type=button value="목록보기"onClick="backToList(this.form)" />목록보기</button>
</div>

  </form>
</body>
</html>
