package com.myspring.pro.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.admin.vo.adminVO;
import com.myspring.pro.project.vo.projectVO;

public interface adminController {
	public ModelAndView listprojects(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listnotice(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String tag(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removetag(@RequestParam ("TAG_THIRD")String tag,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView login(@ModelAttribute("adminVO") adminVO adminVO,
			//RedirectAttributes클래스를 이용해 로그인 실패 시 다시 로그인창으로 리다이렉트하여 실패 메시지 전달!
				              RedirectAttributes rAttr,
		                       HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView logout(HttpServletRequest request, 
			   HttpServletResponse response) throws Exception;
	public ResponseEntity addnotice(MultipartHttpServletRequest multipartRequest,
			 HttpServletResponse response) throws Exception;
	
	public ModelAndView noticeadd(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	public ModelAndView noticeDetail(@RequestParam("NOTICE_CODE") int NOTICE_CODE, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public ModelAndView removenoticefile(@RequestParam("NOTICE_CODE") int NOTICE_CODE,
			@RequestParam("originalFileName") String originalFileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	public ModelAndView removenotice(@RequestParam("NOTICE_CODE") int NOTICE_CODE,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	public ResponseEntity updatenotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;
	//테그관리
	public ModelAndView admintag(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
