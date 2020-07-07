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
import com.myspring.pro.project.vo.projectVO;

public interface projectController {
	public ModelAndView listprojects(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addproject(MultipartHttpServletRequest multipartRequest,@RequestParam("selecttag") List<String> tag,HttpServletResponse response) throws Exception;
	public ModelAndView projectadd(@ModelAttribute("info") projectVO projectVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeproject(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView projectDetail(@RequestParam("PROJECT_CODE") int PROJECT_CODE, 	/*@ModelAttribute("proejcet") projectVO projectVO,*/
	           HttpServletRequest request, HttpServletResponse response) throws Exception;
	  public @ResponseBody String addtag(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request,  
			   HttpServletResponse response) throws Exception;
	//public @ResponseBody String search(@RequestParam("keyword") String keyword,HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ResponseEntity updateproject(MultipartHttpServletRequest multipartRequest,  
			    HttpServletResponse response) throws Exception;
	 public @ResponseBody String memberprojectadd(@RequestParam("PROJECT_CODE") int PROJECT_CODE,HttpServletRequest request,  
	   HttpServletResponse response) throws Exception;
	 public ModelAndView removetag(@RequestParam("PROJECT_CODE") int PROJECT_CODE, @RequestParam("PROJECT_TAG") String tag,  
			  HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchprojects(@RequestParam("searchWord") String searchWord,@RequestParam("searchtag") List<String> tag,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
