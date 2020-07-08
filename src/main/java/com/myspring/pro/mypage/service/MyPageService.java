package com.myspring.pro.mypage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro.member.vo.MemberVO;
import com.myspring.pro.mypage.vo.MyPageVO;
import com.myspring.pro.project.vo.projectVO;

public interface MyPageService {
	 // 내가 등록한 프로젝트 조회.
	 public List<projectVO> selectMyProjectList(String MEMBER_ID)  throws Exception;
	 // 신청 프로젝트 취소하기
	 public void cancelApply(MyPageVO mypageVO)  throws Exception;
	 // 회원 정보 수정
	 public MemberVO modifyMyInfo(Map memberMap) throws Exception;
	 // 회원 수정 (해쉬 태그 추가)
	 public int addTag(MemberVO membervo) throws DataAccessException;
	 // 회원 수정 (해쉬 태그 삭제)
	 public int removeTag(MemberVO membervo) throws DataAccessException;
	 // 회원 상세 창
	 public MemberVO myDetailInfo(String MEMBER_ID) throws Exception;
	 // 회원 태그리스트 조회
	 public List<MemberVO> selectTagList(String MEMBER_ID) throws Exception;
	 // 프로젝트 리스트 조회.
	 public List selectApplyProjectList(String MEMBER_ID) throws DataAccessException;
}
