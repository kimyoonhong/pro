package com.myspring.pro.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.member.vo.MemberVO;

public interface MemberDAO {
	 // 프로젝트에 신청한 회원 정보 조회
	 public List<MemberVO> selectMemberInfo(MemberVO memberVO) throws DataAccessException;
	 // 회원조회
	 public List selectAllMemberList() throws DataAccessException;
	 // 로그인
	 public MemberVO login(MemberVO  memberVO) throws DataAccessException;
	 // 회원가입 && 회원 해쉬 태그 Insert
	 public int addMember(MemberVO memberVO,List<String> tagVO) throws DataAccessException;
	 // 회원 삭제
	 public int deleteMember(String MEMBER_ID) throws DataAccessException;
	 // ID 중복 확인
	 public String selectOverlappedID(String MEMBER_ID) throws DataAccessException;
	 
}
