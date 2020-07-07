package com.myspring.pro.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


import com.myspring.pro.project.vo.projectVO;

public interface projectService {
	public  List listprojects() throws  DataAccessException;
	
	public List TagList() throws DataAccessException;
	
	public List projectTagList(int PROJECT_CODE) throws DataAccessException;
	
	public void addprojecttag(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public int addproject(Map projectMap,List<String> tag) throws DataAccessException;
	
	public void removeproject(int PROJECT_CODE) throws DataAccessException;
	
	public boolean overlappedTag (Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public void addmemberproject (Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public boolean overlappedMemberproject (Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public void removetag(int PROJECT_CODE,String tag) throws DataAccessException;

	public projectVO viewproject (int PROJECT_CODE) 	throws DataAccessException;
	
	public void modproject(Map projectMap) throws Exception;
	
	//public int insertprojecttaglist(Map projectMap,List<String> tag) throws DataAccessException;
	
	//public List<String> search(String keyword) throws Exception;
	
	public List<String> searchprojects(String searchkeyword,List<String> tag) throws Exception; 
	
}
