package com.myspring.pro.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;

public interface MemberService {
	 public List listMembers() throws DataAccessException;
	 public void addMember(MemberVO memberVO) throws DataAccessException;
	 public int deleteMember(String MEMBER_ID) throws DataAccessException;
	 public MemberVO login(MemberVO  memberVO) throws Exception;
	 public String overlapped(String MEMBER_ID) throws Exception;
}
