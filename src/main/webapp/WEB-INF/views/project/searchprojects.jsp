<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");

%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="test" href="${contextPath}/resources/test/test.js" type="text/css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
 function pagefn(){
	 var str = ;
	 alert(str);
 }
</script>
<script type="text/javascript">
    
    var totalData = 1000;    // 총 데이터 수
    var dataPerPage = 20;    // 한 페이지에 나타낼 데이터 수
    var pageCount = 10;        // 한 화면에 나타낼 페이지 수
    
    function paging(totalData, dataPerPage, pageCount, currentPage){
        
        console.log("currentPage : " + currentPage);
        
        var totalPage = Math.ceil(totalData/dataPerPage);    // 총 페이지 수
        var pageGroup = Math.ceil(currentPage/pageCount);    // 페이지 그룹
        
        console.log("pageGroup : " + pageGroup);
        
        var last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호
        if(last > totalPage)
            last = totalPage;
        var first = last - (pageCount-1);    // 화면에 보여질 첫번째 페이지 번호
        var next = last+1;
        var prev = first-1;
        
        console.log("last : " + last);
        console.log("first : " + first);
        console.log("next : " + next);
        console.log("prev : " + prev);
 
        var $pingingView = $("#paging");
        
        var html = "";
        
        if(prev > 0)
            html += "<a href=# id='prev'><</a> ";
        
        for(var i=first; i <= last; i++){
            html += "<a href='#' id=" + i + ">" + i + "</a> ";
        }
        
        if(last < totalPage)
            html += "<a href=# id='next'>></a>";
        
        $("#paging").html(html);    // 페이지 목록 생성
        $("#paging a").css("color", "black");
        $("#paging a#" + currentPage).css({"text-decoration":"none", 
                                           "color":"red", 
                                           "font-weight":"bold"});    // 현재 페이지 표시
                                           
        $("#paging a").click(function(){
            
            var $item = $(this);
            var $id = $item.attr("id");
            var selectedPage = $item.text();
            
            if($id == "next")    selectedPage = next;
            if($id == "prev")    selectedPage = prev;
            
            paging(totalData, dataPerPage, pageCount, selectedPage);
        });
                                           
    }
    
    $("document").ready(function(){        
        paging(totalData, dataPerPage, pageCount, 1);
    });
</script>
</head>
<body>
<c:set var="str" value="11" scope="page"/>
<table border="1"  align="center"  width="80%">
    <tr align="center"   bgcolor="lightgreen">
      <td><b>글번호</b></td>
      <td><b>제목</b></td>
      <td><b>작성일</b></td>
              <c:choose>
       <c:when test="${isLogOn == true  && admin!= null}">
      <td>삭제하기</td>
      </c:when>
           </c:choose>  
   </tr>
 <c:forEach var="search" items="${projectsList}" varStatus="pa" >
   <tr align="center">

    <td>${pa.count}</td>
	  <td><a class='cls1' href="${contextPath}/project/projectDetail.do?PROJECT_CODE=${search.PROJECT_CODE}">${search.PROJECT_TITTLE }</a></td>
      <td>${search.PROJECT_REGISTER_DAY}</td>
       <c:choose>
       <c:when test="${isLogOn == true  && admin!= null}">
      <td><a href="${contextPath}/project/removeproject.do?PROJECT_CODE=${project.PROJECT_CODE}">삭제하기</a></td>
      </c:when>
     </c:choose>  
    </tr>
  </c:forEach>
</table>
</body>
</html>