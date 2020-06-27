package com.myspring.pro.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;

public interface MemberDAO {
	 // 회원조회
	 public List selectAllMemberList() throws DataAccessException;
	 // 로그인
	 public MemberVO login(MemberVO  memberVO) throws DataAccessException;
	 // 회원 등록
	 public void insertMember(MemberVO memberVO) throws DataAccessException;
	 // 회원 삭제
	 public int deleteMember(String MEMBER_ID) throws DataAccessException;
	 // ID 중복 확인
	 public String selectOverlappedID(String MEMBER_ID) throws DataAccessException;
	 
}
