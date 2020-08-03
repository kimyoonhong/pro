package com.myspring.pro.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


import com.myspring.pro.project.vo.projectVO;

public interface projectService {
	
	public  List listprojects() throws  DataAccessException;
	
	public List TagList() throws DataAccessException;
	
	public List ALLprojecttagList() throws DataAccessException;
	
	public List projectTagList(int PROJECT_CODE) throws DataAccessException;
	
	public void addprojecttag(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public int addproject(Map projectMap,List<String> tag) throws DataAccessException;
	
	public void removeproject(int PROJECT_CODE) throws DataAccessException;
	
	public boolean overlappedTag (Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public void addmemberproject (Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public String overlappedMemberproject (Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public void removetag(int PROJECT_CODE,String tag) throws DataAccessException;
	
	public void removefile(int PROJECT_CODE) throws DataAccessException;

	public projectVO viewproject (int PROJECT_CODE) 	throws DataAccessException;
	
	public void modproject(Map projectMap) throws Exception;
	
	public void updateapply_ck(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	//public int insertprojecttaglist(Map projectMap,List<String> tag) throws DataAccessException;
	
	//public List<String> search(String keyword) throws Exception;
	
	public List<String> searchprojects(String searchkeyword,List<String> tag) throws Exception; 
	
	public List memberprojectlist(int PROJECT_CODE) throws DataAccessException;
	
	public void memberpass_ck(Map projectMap ,int PROJECT_CODE) throws DataAccessException;
	
	public List tag_firstList() throws DataAccessException;
	
	public List tag_second(String TAG_FIRST) throws DataAccessException;

	public List tag_third(String TAG_SECOND) throws DataAccessException;

	
}
