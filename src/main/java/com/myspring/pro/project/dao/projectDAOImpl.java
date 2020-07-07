package com.myspring.pro.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public List selectprojectTagList(int PROJECT_CODE) throws DataAccessException{
		List<projectVO> projectTagList = null;
		projectTagList = sqlSession.selectList("mapper.project.selectprojectTagList",PROJECT_CODE);
		return projectTagList;
	}
	
	@Override
	public List selectTagList() throws DataAccessException {
		List<tagVO> projectTagList = null;
		projectTagList = sqlSession.selectList("mapper.tag.selectTagList");
		return projectTagList;
	}

	@Override
	public int insertproject(Map projectMap,List<String> tag) throws DataAccessException {
		int projcet_code = selectprojectcode();
		projectVO.setPROJECT_CODE(projcet_code);
		projectMap.put("PROJCET_CODE",projcet_code);
		sqlSession.insert("mapper.project.insertproject",projectMap);
		insertprojecttaglist(projectMap,tag);
		return projcet_code;
	}
	
    @Override
    public int selectprojectcode() throws DataAccessException{
    	int projcet_code = sqlSession.selectOne("mapper.project.selectprojectcode");
    	System.out.println("코드111"+projcet_code);
    	return projcet_code;
    }
    
	@Override
	public void deleteproject(int PROJECT_CODE) throws DataAccessException {
		//int result = 
				sqlSession.delete("mapper.project.deleteproject", PROJECT_CODE);
		//return result;
	}
	
	
	@Override
	public void insertprojecttaglist(Map projectMap,List<String> tag) throws DataAccessException{
		int i;
		projectVO.setPROJECT_TITTLE( (String) projectMap.get("PROJECT_TITTLE"));
	
		
		if(!tag.get(tag.size()-1).equals("null"))
		for(i=1;i<tag.size();i++) {
			projectVO.setPROJECT_TAG(tag.get(i));
			sqlSession.insert("mapper.project.insertprojecttag",projectVO);
		}
		
	}
	@Override
	public void insetprojecttag(Map projectMap,int PROJECT_CODE) throws DataAccessException{
		
		 
		  projectVO.setPROJECT_CODE(PROJECT_CODE);
		 
		projectVO.setPROJECT_TITTLE((String)projectMap.get("PROJECT_TITTLE"));
		projectVO.setPROJECT_TAG((String)projectMap.get("PROJECT_TAG"));
		System.out.println("VO확인 코드 : "+projectVO.getPROJECT_CODE());
		System.out.println("VO확인 : "+projectVO.getPROJECT_TITTLE());
		System.out.println("VO확인 : "+projectVO.getPROJECT_TAG());
		sqlSession.insert("mapper.project.insertprojecttag",projectVO);
	}
	@Override
	public void removetag(int PROJECT_CODE,String tag) throws DataAccessException{
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		projectVO.setPROJECT_TAG(tag);
		
		System.out.println("코드 :"+projectVO.getPROJECT_CODE()+" 테크 : "+projectVO.getPROJECT_TAG());
		sqlSession.delete("mapper.project.deleteprojecttag",projectVO);
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

	@Override
	public projectVO selectviewprojectList(int PROJECT_CODE) throws DataAccessException {
		projectVO viewproject = sqlSession.selectOne("mapper.project.selectviewproject",PROJECT_CODE);
		return viewproject;
	}

	@Override
	public void updateproject(Map projectMap) throws DataAccessException {
		sqlSession.update("mapper.project.updateproject",projectMap);
	}

	@Override
	public boolean selectoverlappedtag(Map projectMap,int PROJECT_CODE) throws DataAccessException {
		 projectVO.setPROJECT_CODE(PROJECT_CODE);
		 boolean a;
			projectVO.setPROJECT_TITTLE((String)projectMap.get("PROJECT_TITTLE"));
			projectVO.setPROJECT_TAG((String)projectMap.get("PROJECT_TAG"));
			String b=sqlSession.selectOne("mapper.project.selectoverlappedtag",projectVO);
				if(!sqlSession.selectOne("mapper.project.selectoverlappedtag",projectVO).equals("true")) {
					a=true;
				}else {
					a=false;
				}
				System.out.println("b 확인"+ b);
		System.out.println("a 확인"+ a);
		
		return a;
	}

	@Override
	public boolean selectoverlappedmemberproject(Map projectMap, int PROJECT_CODE) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insetmemberproject(Map projectMap, int PROJECT_CODE) throws DataAccessException {
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		projectVO.setAPPLY_CK((String)projectMap.get("APPLY_CK"));
		projectVO.setMEMBER_ID((String)projectMap.get("MEMBER_ID"));
		
		sqlSession.insert("insertprojectmemberproject",projectVO);
		
	}

}

