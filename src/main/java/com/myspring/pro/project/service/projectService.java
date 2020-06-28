package com.myspring.pro.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


import com.myspring.pro.project.vo.projectVO;

public interface projectService {
	public  List listprojects() throws  DataAccessException;
	public List TagList() throws DataAccessException;
	//public List<String> search(String keyword) throws Exception;
	 public int addproject(projectVO projectVO) throws DataAccessException;
	 public int removeproject(String PROJECT_CODE) throws DataAccessException;
	 public List<String> searchprojects(String searchkeyword,List<String> tag) throws Exception; 
	
}
