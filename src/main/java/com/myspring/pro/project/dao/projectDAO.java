package com.myspring.pro.project.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.project.vo.projectVO;

public interface projectDAO {
	public List selectAllprojectList() throws DataAccessException;
	public List selectTagList() throws DataAccessException;
	 public int insertproject(projectVO projectVO) throws DataAccessException ;
	 public int deleteproject(String PROJECT_CODE) throws DataAccessException;
	 public List<String> selectKeywordSearch(String keyword,List<String> tag) throws DataAccessException;
}
