package com.myspring.pro.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro.member.vo.MemberVO;

@Repository("mypageDAO")
public class MyPageDAOImpl implements MyPageDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 회원 정보 수정
	@Override
	public void modifyMyInfo(Map memberMap) throws DataAccessException{
		sqlSession.update("mapper.member.modifyMyInfo",memberMap);
	}
	
	// 회원 상세
	@Override
	public MemberVO MyDetailInfo(String MEMBER_ID) throws DataAccessException{
		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.member.MyDetailInfo",MEMBER_ID);
		return memberVO;
		
	}
	
	// 회원 태그 리스트 불러오기
	@Override
	public List<MemberVO> selectTagList(String MEMBER_ID) throws DataAccessException{
		List<MemberVO> MemberTagList = null;
		 MemberTagList = sqlSession.selectList("mapper.member.selectTagList",MEMBER_ID);
		 System.out.println("다오에서 해쉬태그 사이즈"+ MemberTagList.size());
		 
		 for(int i = 0; i<MemberTagList.size(); i++) {
			 
			 	System.out.println(MemberTagList.get(i));
		 }
		 
		 return MemberTagList;
	}
	
	// 회원 수정 시 해쉬태그 추가
	@Override
	public int addTag(MemberVO membervo) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.addMember_tagList",membervo);
		return result;
	}
	
	 // 회원 수정 (해쉬태그 삭제)
	@Override
	 public int removeTag(MemberVO memberVO) throws DataAccessException {
		 int result = sqlSession.delete("mapper.member.removeTag", memberVO);
		 System.out.println("다오에서 아이디"+memberVO.getMEMBER_ID());
		 System.out.println("삭제 시 결과 = "+result);
		 return result;
	 }
	
	// 나의 프로젝트 조회
		@Override
		public List selectMyProjectList(String MEMBER_ID) throws DataAccessException {
			List MyProjectList = null;
			MyProjectList = sqlSession.selectList("mapper.mypage.selectMyProjectList",MEMBER_ID);
			return MyProjectList;
		}
		
	
	
}