package com.myspring.pro.project.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.member.vo.MemberVO;
import com.myspring.pro.page.Criteria;
import com.myspring.pro.page.PageMaker;
import com.myspring.pro.project.vo.projectVO;

public interface projectController {
	//전체 프로젝트  조회
	public ModelAndView listprojects(/*@RequestParam("TAG_FIRST") String TAG_FIRST ,*/ PageMaker pageMaker,Criteria cri,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//메인화면
	public ModelAndView mainprojectList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//검색을 위한 테그
	public void tagList (HttpServletRequest request, HttpServletResponse response) throws Exception;
	//프로젝트 추가
	public ResponseEntity addproject(MultipartHttpServletRequest multipartRequest,@RequestParam("selecttag") List<String> tag,HttpServletResponse response) throws Exception;
	//프로젝트 추가
	public ModelAndView projectadd(@ModelAttribute("info") projectVO projectVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	//프로젝트 삭제
	public ModelAndView removeproject(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//프로젝트 수정
	public ResponseEntity updateproject(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	//프로젝트 상세보기
	public ModelAndView projectDetail(@RequestParam("PROJECT_CODE") int PROJECT_CODE,HttpServletRequest request, HttpServletResponse response) throws Exception;
	//프로젝트 테그추가
    public @ResponseBody String addtag(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request,HttpServletResponse response) throws Exception;
    //프로젝트 테그 삭제
  	public ModelAndView removetag(@RequestParam("PROJECT_CODE") int PROJECT_CODE, @RequestParam("PROJECT_TAG") String tag,  
  		  HttpServletRequest request, HttpServletResponse response) throws Exception;
	//프로젝트 신청,찜하기
    public @ResponseBody String memberprojectadd(@RequestParam("PROJECT_CODE") int PROJECT_CODE,HttpServletRequest request,  
	   HttpServletResponse response) throws Exception;
	 //프로젝트 검색
	public ModelAndView searchprojects(@RequestParam("searchWord") String searchWord,@RequestParam("searchtag") List<String> tag,HttpServletRequest request, HttpServletResponse response) throws Exception;
	//합격 유무 변경
	public @ResponseBody String memberpass_ck(@RequestParam("PROJECT_CODE") int PROJECT_CODE,HttpServletRequest request,  
	   HttpServletResponse response) throws Exception;
	public ModelAndView pageing(@RequestParam("page") String page,PageMaker pageMaker,Criteria cri,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView removefile(@RequestParam("PROJECT_CODE") int PROJECT_CODE,
			@RequestParam("originalFileName") String originalFileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	/*  public ModelAndView tagtag(@RequestParam("TAG_FIRST") String TAG_FIRST ,@RequestParam("TAG_SECOND") String TAG_SECOND
	 ,HttpServletRequest request, HttpServletResponse response) throws Exception;*/
	 
}
