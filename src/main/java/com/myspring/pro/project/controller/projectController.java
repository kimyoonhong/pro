package com.myspring.pro.project.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.project.vo.projectVO;

public interface projectController {
	public ModelAndView listprojects(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addproject(@ModelAttribute("info") projectVO projectVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeproject(@RequestParam("PROJECT_CODE") String PROJECT_CODE, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//public @ResponseBody String search(@RequestParam("keyword") String keyword,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchprojects(@RequestParam("searchWord") String searchWord,@RequestParam("searchtag") List<String> tag,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
