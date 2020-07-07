package com.myspring.pro.member.controller;


import java.util.List;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.member.vo.MemberVO;
import com.myspring.pro.project.vo.tagVO;

public interface MemberController {
	// 로그인
	public ModelAndView login(@ModelAttribute("memberVO") MemberVO memberVO,
            RedirectAttributes rAttr,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	// 로그아웃
	public ModelAndView logout(HttpServletRequest request, 
								HttpServletResponse response) throws Exception;
	
	// 회원가입 && 해쉬태그
	public ResponseEntity  addMember(@ModelAttribute("memberVO") MemberVO memberVO,
									 @RequestParam("tagVO") List<String> tagVO,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// ID중복확인
	public ResponseEntity overlapped(@RequestParam("MEMBER_ID") String MEMBER_ID,
									HttpServletRequest request, 
									HttpServletResponse response) throws Exception;
	
	// 회원 출력
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) throws Exception;
	
}