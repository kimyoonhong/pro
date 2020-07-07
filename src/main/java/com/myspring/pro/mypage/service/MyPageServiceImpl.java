package com.myspring.pro.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro.member.dao.MemberDAO;
import com.myspring.pro.member.vo.MemberVO;

import com.myspring.pro.mypage.dao.MyPageDAO;

@Service("mypageService")
@Transactional(propagation = Propagation.REQUIRED)
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private MyPageDAO mypageDAO;
	
	// 회원 수정 시 해쉬태그 추가
	@Override
	public int addTag(MemberVO membervo) throws DataAccessException {
		 return mypageDAO.addTag(membervo);
	}

	// 회원 정보 수정
	@Override
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception {
		String MEMBER_ID=(String)memberMap.get("MEMBER_ID");
		mypageDAO.modifyMyInfo(memberMap);
		 return (MemberVO)mypageDAO.MyDetailInfo(MEMBER_ID);
	}
	
	// 회원 상세 창
	@Override
	public MemberVO myDetailInfo(String MEMBER_ID) throws Exception{
		return mypageDAO.MyDetailInfo(MEMBER_ID);
	}
	
	// 회원 수정 (해쉬 태그 삭제)
	@Override
	public int removeTag(MemberVO memberVO) throws DataAccessException{
		return mypageDAO.removeTag(memberVO);
	}
	
	// 회원 태그리스트 조회
	public List<MemberVO> selectTagList(String MEMBER_ID) throws Exception{
		return mypageDAO.selectTagList(MEMBER_ID);
	}
	
	 // 프로젝트 리스트 조회.
	 public List selectMyProjectList(String MEMBER_ID) throws DataAccessException{
		 return mypageDAO.selectMyProjectList(MEMBER_ID);
	 }
}
