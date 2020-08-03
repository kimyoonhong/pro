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
 <script src="//code.jquery.com/jquery-3.3.1.js"></script> 
<script type="text/javascript">

 function backToList(obj){
 obj.action="${contextPath}/qna/listArticles.do";
 obj.submit();
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
 
  function readURL(input) {
      if (input.files && input.files[0]) {
          var reader = new FileReader();
          reader.onload = function (e) {
              $('#preview').attr('src', e.target.result);
          }
          reader.readAsDataURL(input.files[0]);
      }
  }  
</script> 
<title>답글쓰기 페이지</title>
</head>

<body>
 <h1>답글쓰기</h1>
  <form name="frmReply" method="post"  action="${contextPath}/qna/addReply.do"   enctype="multipart/form-data">
    <table>
    <tr>
			<td align="right"> 작성자:&nbsp; </td>
			<td><input type="text" size="20" maxlength="100"  value="${member.MEMBER_NAME }" name="writer"></input> </td>
		</tr>
	</tr>
	<tr>
    <td align="right">QNA 유형: </td>
	<td colspan="2"  align="left">
	<!-- style='width:100px; height:22px;' -->
	<select  onchange="Check_Email()" id="dd" name="QNA_TAG"  >
	<c:forEach var="tag" items="${tagList}">
            <option value="${tag.QNA_TAG}">${tag.QNA_TAG}</option>
    </c:forEach>
    </select>
    </td>
		<tr>
			<td align="right">제목:&nbsp;  </td>
			<td><input type="text" size="67"  maxlength="500"   name="QNA_TITLE"> </input></td>
		</tr>
		<tr>
			<td align="right" valign="top"><br>내용:&nbsp; </td>
			<td><textarea name="QNA_CONTENT" rows="10" cols="65" maxlength="4000"> </textarea> </td>
		</tr>
		<tr>
			<td align="right">비밀번호:&nbsp;  </td>
			<td><input type="password" size="10" maxlength="12" name="passwd"> </input> </td>
		</tr>
		
			<td align="right">이미지파일 첨부:  </td>
			<td> <input type="file" name="IMAGEFILENAME"  onchange="readURL(this);" /></td>
            <td><img  id="preview" src="#"   width=200 height=200/></td>
		</tr>
		<tr>
			<td align="right"> </td>
			<td>
				<input type=submit value="답글쓰기" />
				<input type=button value="취소"onClick="backToList(this.form)" />
				
			</td>
		</tr>
    
    </table>
  
  </form>
</body>
</html>