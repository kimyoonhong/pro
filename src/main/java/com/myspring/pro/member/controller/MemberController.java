package com.myspring.pro.member.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
            RedirectAttributes rAttr,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity  addMember(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity overlapped(@RequestParam("MEMBER_ID") String MEMBER_ID,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
	
	
	
}