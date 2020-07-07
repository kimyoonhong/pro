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

	// 회원가입 && 회원 해쉬 태그 Insert
	@Override
	public int addMember(MemberVO memberVO,List<String> tagVO) throws DataAccessException {
		
		int i=0;
		System.out.println("회원가입 아이디 : "+ memberVO.getMEMBER_ID());
		// 회원가입 정보 DB에 저장. ( Insert into T_PROJECT_MEMBER )
		sqlSession.selectOne("mapper.member.insertMember",memberVO);
		
		
		// 해쉬태그 최대 5개이기 때문에 배열로 받아와야한다.
		// MemberVO에 배열로 만들지 않은이유: XML에서 SQL문을 실행할때 배열이 들어가지않는다.
		for (i = 0 ; i < tagVO.size() ; i++) {
			// tagVO에 들어있는 해쉬태그 정보를 memberVO의 TAG 속성에 하나씩 넣는다.
			System.out.println("******************:::::"+tagVO.size());
			memberVO.setTAG(tagVO.get(i));
			// 하나씩 불러와서
			String tag = memberVO.getTAG();
			// 테스트
			System.out.println("들어간 태그 이름은 :" + tag);
			// 하나씩 넣는다.
		    sqlSession.insert("mapper.member.addMember_tagList",memberVO);
		}
		return i;
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