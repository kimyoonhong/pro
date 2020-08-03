package com.myspring.pro.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.pro.admin.dao.adminDAO;
import com.myspring.pro.admin.vo.adminVO;
@Service("adminService")
public class adminServiceImpl implements adminService {
	@Autowired
	private adminDAO adminDAO;
	@Override
	public void admintag(Map adminMap) throws DataAccessException {
		// TODO Auto-generated method stub
		adminDAO.inserttag(adminMap);
		
	}

	@Override
	public boolean overtag(Map adminMap) throws DataAccessException {
		
		return adminDAO.overtag(adminMap);
	}

	@Override
	public void removertag(String tag) {
		 adminDAO.deletetag(tag);
	}

	@Override
	public adminVO login(adminVO adminVO) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.login(adminVO);
	}

	@Override
	public List t_noticeList() throws DataAccessException {
		// TODO Auto-generated method stub
		return adminDAO.select_t_notice();
	}

	@Override
	public int addt_notice(Map adminMap) throws DataAccessException {
		return adminDAO.insert_t_notice(adminMap);
		
	}

	@Override
	public adminVO viewproject(int NOTICE_CODE) throws DataAccessException {
		// TODO Auto-generated method stub
		return adminDAO.selectviewproject(NOTICE_CODE);
	}

	@Override
	public void updatenotice(Map noticeMap) throws DataAccessException {
		adminDAO.updatenotice(noticeMap);
		
	}

	@Override
	public void removenoticefile(int NOTICE_CODE) throws DataAccessException {
		// TODO Auto-generated method stub
		adminDAO.removenoticefile(NOTICE_CODE);
	}

	@Override
	public void removenotice(int NOTICE_CODE) throws DataAccessException {
		adminDAO.removenotice(NOTICE_CODE);
		
	}



}
