package com.myspring.pro.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;
import com.myspring.pro.mypage.vo.MyPageVO;
import com.myspring.pro.project.vo.projectVO;

public interface MyPageDAO {
	 
	 // 회원 태그 중복 확인
	 public String selectOverlappedTag(MemberVO memberVO) throws DataAccessException;
	 // 내가 등록한 프로젝트 조회
	 public List<projectVO> selectMyProjectList(String MEMBER_ID)  throws Exception;
	 // 프로젝트 관심-> 신청으로 변경
	 public void modifyAPPLY_CK(MyPageVO mypageVO)  throws Exception;
	 // 신청 프로젝트 취소
	 public void cancelApply(MyPageVO mypageVO)  throws Exception;
	 // 회원 정보 수정
	 public void  modifyMyInfo(Map memberMap) throws Exception;
	 // 회원 상세
	 public MemberVO MyDetailInfo(String MEMBER_ID) throws DataAccessException;
	 // 회원 태그 리스트 불러오기
	 public List<MemberVO> selectTagList(String MEMBER_ID) throws DataAccessException;
	 // 회원 수정 (해쉬태그 추가)
	 public int addTag(MemberVO membervo) throws DataAccessException;
	 // 회원 수정 (해쉬태그 삭제)
	 public int removeTag(MemberVO memberVO) throws DataAccessException;
	 // 프로젝트 리스트 조회.
	 public List selectApplyProjectList(String MEMBER_ID) throws DataAccessException;
}
