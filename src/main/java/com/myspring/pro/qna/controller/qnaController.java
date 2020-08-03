package com.myspring.pro.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/*import com.myspring.pro.qna.vo.Criteria;
import com.myspring.pro.qna.vo.PageMaker;*/
import com.myspring.pro.page.Criteria;
import com.myspring.pro.page.PageMaker;


public interface qnaController {
	
	//게시글 목록 조회
	public ModelAndView list(PageMaker pageMaker, Criteria cri, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//게시글 작성
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
	//글 상세창 조회
	public ModelAndView viewArticle(@RequestParam("QNA_ARTICLENO") int QNA_ARTICLENO,
			                        HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//게시글 삭제
	public ResponseEntity removeArticle(@RequestParam("QNA_ARTICLENO") int QNA_ARTICLENO,
                              HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
}
