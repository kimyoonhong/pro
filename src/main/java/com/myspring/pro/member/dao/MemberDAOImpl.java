package com.myspring.pro.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	// 회원 조회
	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}
	
	// 로그인
	@Override
	public MemberVO login(MemberVO  memberVO) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.login",memberVO);
	   return member;
	}

	// 회원 등록
	@Override
	public void insertMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertMember", memberVO);
		
	}
	
	// 회원 삭제
	@Override
	public int deleteMember(String MEMBER_ID) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", MEMBER_ID);
		return result;
	}
	
	// ID 중복 확인
	@Override
	public String selectOverlappedID(String MEMBER_ID) throws DataAccessException {
		String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",MEMBER_ID);
		return result;
	}
	
	
	
	
	
}