package com.myspring.pro.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;

public interface MyPageDAO {
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
	 public List selectMyProjectList(String MEMBER_ID) throws DataAccessException;
}
