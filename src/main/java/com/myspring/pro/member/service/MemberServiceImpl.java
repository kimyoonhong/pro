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

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}

	@Override
	public void addMember(MemberVO member) throws DataAccessException {
		 memberDAO.insertMember(member);
	}

	@Override
	public int deleteMember(String MEMBER_ID) throws DataAccessException {
		return memberDAO.deleteMember(MEMBER_ID);
	}
	
	@Override
	public String overlapped(String MEMBER_ID) throws Exception{
		return memberDAO.selectOverlappedID(MEMBER_ID);
	}
	
	@Override
	public MemberVO login(MemberVO  memberVO) throws Exception{
		return memberDAO.login(memberVO);
	}
}
