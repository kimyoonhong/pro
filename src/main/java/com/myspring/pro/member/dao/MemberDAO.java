package com.myspring.pro.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;

public interface MemberDAO {
	 public List selectAllMemberList() throws DataAccessException;
	 public void insertMember(MemberVO memberVO) throws DataAccessException ;
	 public int deleteMember(String MEMBER_ID) throws DataAccessException;
	 public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	 public String selectOverlappedID(String MEMBER_ID) throws DataAccessException;
	 public MemberVO login(MemberVO  memberVO) throws DataAccessException;
}
