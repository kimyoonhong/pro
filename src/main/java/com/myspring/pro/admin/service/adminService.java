package com.myspring.pro.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.admin.vo.adminVO;
import com.myspring.pro.member.vo.MemberVO;
import com.myspring.pro.project.vo.projectVO;

public interface adminService {
	public  void admintag(Map adminMap) throws DataAccessException;
	public  boolean overtag(Map adminMap) throws DataAccessException;
	public void removertag(String tag) throws DataAccessException;
	public adminVO login(adminVO  adminVO) throws Exception;
	public List t_noticeList() throws DataAccessException;
	public int addt_notice(Map adminMap) throws DataAccessException;
	public adminVO viewproject (int NOTICE_CODE) throws DataAccessException;
	public void updatenotice(Map noticeMap) throws DataAccessException;
	public void removenoticefile(int NOTICE_CODE) throws DataAccessException;
	public void removenotice(int NOTICE_CODE) throws DataAccessException;
	
}
