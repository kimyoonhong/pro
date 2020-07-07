package com.myspring.pro.mypage.controller;


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

public interface MyPageController {
	
	// 나의 프로젝트 조회
	public ModelAndView myProject(HttpServletRequest request, 
										HttpServletResponse response) 
													throws Exception;
	// 회원 수정 (해쉬태그 추가)
	public String addTag(@RequestParam("MEMBER_ID") String MEMBER_ID,
			 						 @RequestParam("TAG_THIRD") String TAG_THIRD,
									HttpServletRequest request,
									HttpServletResponse response) throws Exception;
	// 회원 수정(해쉬태그 삭제)
	public ModelAndView removeTag(@RequestParam("MEMBER_ID") String MEMBER_ID,
			  					  @RequestParam("MEMBER_TAG") String MEMBER_TAG,
			  					  HttpServletRequest request, 
			 					  HttpServletResponse response)  throws Exception;
	// 회원정보 수정
	public ResponseEntity modifyMyInfo(
									   @ModelAttribute("memberVO") MemberVO memberVO,
									   @RequestParam("attribute")  String attribute,
            						   @RequestParam("value")  String value,
            						   HttpServletRequest request, 
            						   HttpServletResponse response)  throws Exception;
            						   
	// 회원 상세
	public ModelAndView myDetailInfo(HttpServletRequest request, 
									HttpServletResponse response)  throws Exception;
}