package com.myspring.pro.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro.admin.vo.adminVO;
import java.util.List;
import java.util.Map;
@Repository("adminDAO")
public class adminDAOImpl implements adminDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired 
	private adminVO adminVO = new adminVO();
	 
	
	@Override
	public void inserttag(Map adminMap) throws DataAccessException {
		
		sqlSession.insert("mapper.tag.inserttag",adminMap);
	}

	@Override
	public boolean overtag(Map adminMap) throws DataAccessException {
		boolean over = false;
		String tag = sqlSession.selectOne("mapper.tag.adminovertag",adminMap);
		if(tag.equals("true")) {
			over=false;
		}else if(tag.equals("false")) {
			over=true;
		}
		return over;
	}

	@Override
	public void deletetag(String tag) throws DataAccessException {
		sqlSession.delete("mapper.tag.deletetag", tag);
	}

	@Override
	public adminVO login(adminVO adminVO) throws DataAccessException {
		adminVO admin = (adminVO)sqlSession.selectOne("mapper.admin.login",adminVO);
		return admin;
	}

	@Override
	public List select_t_notice() throws DataAccessException {
		List<adminVO> t_noticeList = sqlSession.selectList("mapper.admin.select_t_notice");
		return t_noticeList;
	}
	
	@Override
	public int insert_t_notice(Map adminMap) throws DataAccessException {
		int NOTICE_CODE= selectNOTICE_CODE();
		System.out.println("NOTICE_CODE : " +NOTICE_CODE);
		adminMap.put("NOTICE_CODE", NOTICE_CODE);
		sqlSession.insert("mapper.admin.insert_t_notice",adminMap);
		
		return NOTICE_CODE;
	}
	
	private int selectNOTICE_CODE() throws DataAccessException{
		int NOTICE_CODE =0;
		if(sqlSession.selectOne("mapper.admin.selectnotice_code")==null) {
			NOTICE_CODE=1;
		}else {
			NOTICE_CODE =sqlSession.selectOne("mapper.admin.selectnotice_code");
		}
		 
		return NOTICE_CODE;
	}

	@Override
	public adminVO selectviewproject(int NOTICE_CODE) throws DataAccessException {
		adminVO.setNOTICE_CODE(NOTICE_CODE);
		return sqlSession.selectOne("mapper.admin.selectviewnotice",adminVO);
	}
	
	@Override
	public void updatenotice(Map noticeMap) throws DataAccessException {
		sqlSession.update("mapper.admin.updatenotice",noticeMap);
	}

	@Override
	public void removenoticefile(int NOTICE_CODE) throws DataAccessException {
		adminVO.setNOTICE_CODE(NOTICE_CODE);
		sqlSession.update("mapper.admin.updateremovenoticefile",adminVO);
	}

	@Override
	public void removenotice(int NOTICE_CODE) throws DataAccessException {
		adminVO.setNOTICE_CODE(NOTICE_CODE);
		sqlSession.delete("mapper.admin.deletenotice",adminVO);
	}
	
}
