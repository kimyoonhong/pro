package com.myspring.pro.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro.member.dao.MemberDAO;
import com.myspring.pro.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	// 프로젝트에 신청한 회원 정보 조회
	@Override
	public List<MemberVO> selectMemberInfo(MemberVO memberVO) throws DataAccessException {
		return  memberDAO.selectMemberInfo(memberVO);
	}
	
	// 회원 출력
	@Override
	public List listMembers() throws DataAccessException {
		List membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	// 회원가입 && 회원 해쉬 태그 Insert
	@Override
	public int addMember(MemberVO member,List<String> tagVO) throws DataAccessException {
		 return memberDAO.addMember(member,tagVO);
	}
	
	// 회원 삭제
	@Override
	public int deleteMember(String MEMBER_ID) throws DataAccessException {
		return memberDAO.deleteMember(MEMBER_ID);
	}
	
	// ID 중복 확인
	@Override
	public String overlapped(String MEMBER_ID) throws Exception{
		return memberDAO.selectOverlappedID(MEMBER_ID);
	}
	
	// 로그인
	@Override
	public MemberVO login(MemberVO  memberVO) throws Exception{
		return memberDAO.login(memberVO);
	}
	
	
}
