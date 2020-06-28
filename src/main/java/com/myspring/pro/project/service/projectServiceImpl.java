package com.myspring.pro.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.myspring.pro.project.dao.projectDAO;
import com.myspring.pro.project.vo.projectVO;
import com.myspring.pro.project.vo.tagVO;


@Service("projectService")
@Transactional(propagation = Propagation.REQUIRED)
public class projectServiceImpl implements projectService {
	@Autowired
	private projectDAO projectDAO;

	
	public List listprojects() throws DataAccessException {
		List projectsList = null;
		projectsList = projectDAO.selectAllprojectList();
		
		return projectsList;
	}

	
	public int addproject(projectVO project) throws DataAccessException {
		return projectDAO.insertproject(project);
	}

	
	public int removeproject(String PROJECT_CODE) throws DataAccessException {
		return projectDAO.deleteproject(PROJECT_CODE);
	}

	
	public List TagList() throws DataAccessException {
		List projectTagList = null;
		projectTagList = projectDAO.selectTagList();
		
		return projectTagList;
	}
	
/*	public List<String> search(String keyword) throws Exception {
		List<String> list=projectDAO.selectKeywordSearch(keyword);
		return list;
	}
*/

	@Override
	public List<String> searchprojects(String searchWord,List<String> tag) throws Exception {
		tag.get(0);
		List<String> list=projectDAO.selectKeywordSearch(searchWord,tag);
		return list;
	}
	
	

}
