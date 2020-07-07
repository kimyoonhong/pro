package com.myspring.pro.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;

public interface MemberService {
	 public List listMembers() throws DataAccessException;
	 // 회원가입 && 회원 해쉬 태그 Insert
	 public int addMember(MemberVO memberVO,List<String> tagVO) throws DataAccessException;
	 // 회원 삭제
	 public int deleteMember(String MEMBER_ID) throws DataAccessException;
	 // 로그인
	 public MemberVO login(MemberVO  memberVO) throws Exception;
	 // ID 중복확인
	 public String overlapped(String MEMBER_ID) throws Exception;
	 
}
