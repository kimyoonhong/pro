package com.myspring.pro.admin.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.admin.vo.adminVO;
import com.myspring.pro.project.vo.projectVO;

import java.util.List;
import java.util.Map;

public interface adminDAO {
	public  boolean overtag(Map adminMap) throws DataAccessException;
	public  void inserttag(Map adminMap) throws DataAccessException;
	public void deletetag(String tag) throws DataAccessException;
	public adminVO login(adminVO  adminVO) throws DataAccessException;
	public List select_t_notice() throws DataAccessException;
	public int insert_t_notice (Map adminMap) throws DataAccessException;
	public adminVO selectviewproject(int NOTICE) throws DataAccessException;
	public void updatenotice(Map noticeMap) throws DataAccessException;
	public void removenoticefile(int NOTICE_CODE) throws DataAccessException;
	public void removenotice(int NOTICE_CODE) throws DataAccessException;
	 
}
