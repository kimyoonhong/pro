package com.myspring.pro.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro.project.vo.tagVO;
import com.myspring.pro.project.vo.projectVO;

@Repository("projectDAO")
public class projectDAOImpl implements projectDAO {
	@Autowired
	private SqlSession sqlSession;
	private projectVO projectVO = new projectVO();

	@Override
	public List selectAllprojectList() throws DataAccessException {
		List<projectVO> projectsList = null;
		projectsList = sqlSession.selectList("mapper.project.selectAllprojectList");
		return projectsList;
	}

	@Override
	public int insertproject(projectVO projectVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.project.insertproject", projectVO);
		return result;
	}

	@Override
	public int deleteproject(String PROJECT_CODE) throws DataAccessException {
		int result = sqlSession.delete("mapper.project.deleteproject", PROJECT_CODE);
		return result;
	}
	
	@Override
	public List selectTagList() throws DataAccessException {
		List<tagVO> projectTagList = null;
		projectTagList = sqlSession.selectList("mapper.tag.selectTagList");
		return projectTagList;
	}
	@Override
	public List<String> selectKeywordSearch(String keyword,List<String> tag) throws DataAccessException {
		int i;
		for(i=0;i<5; i++) {
			if(tag.size()<5) {
				tag.add("null1");
			}
			System.out.println("asd"+tag.get(i));
		}
		projectVO.setTags((String)tag.get(0),(String)tag.get(1),(String)tag.get(2),(String)tag.get(3),(String)tag.get(4));
		projectVO.setKeyword(keyword);
	List<String> list=(ArrayList)sqlSession.selectList("mapper.project.selectKeywordSearch",projectVO);
	
	   return list;
	}

}

