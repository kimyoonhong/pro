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
	@Autowired
	private projectVO projectVO;
	@Autowired
	private tagVO tagVO;

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
		System.out.println("테그 리스트"+tag);
		
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
		for(i=1;i<6; i++) {
			if(tag.size()<6) {
				tag.add("null");
			}
			System.out.println("asd"+tag.get(i));
		}
		projectVO.setTags((String)tag.get(1),(String)tag.get(2),(String)tag.get(3),(String)tag.get(4),(String)tag.get(5));
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
				if(!sqlSession.selectOne("mapper.project.selectoverlappedtag",projectVO).equals("true")) {
					a=true;
				}else {
					a=false;
				}
		System.out.println("a 확인"+ a);
		
		return a;
	}

	@Override
	public String selectoverlappedmemberproject(Map projectMap, int PROJECT_CODE) throws DataAccessException {
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		
		projectVO.setAPPLY_CK((String)projectMap.get("APPLY_CK"));
		projectVO.setMEMBER_ID((String)projectMap.get("MEMBER_ID"));
		
		System.out.println("다오에서 APPLY확인 :"+sqlSession.selectOne("mapper.project.selectoverlappedmemberproject",projectVO));
		
		return sqlSession.selectOne("mapper.project.selectoverlappedmemberproject",projectVO);
	}

	@Override
	public void insetmemberproject(Map projectMap, int PROJECT_CODE) throws DataAccessException {
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		projectVO.setAPPLY_CK((String)projectMap.get("APPLY_CK"));
		projectVO.setMEMBER_ID((String)projectMap.get("MEMBER_ID"));
		sqlSession.insert("insertprojectmemberproject",projectVO);
		
	}

	@Override
	public List selectmemberprojectlist(int PROJECT_CODE) throws DataAccessException {
		List<projectVO> memberList= null;
		memberList=sqlSession.selectList("mapper.project.selectMemberprojectList",PROJECT_CODE);
		return memberList;
	}

	@Override
	public void updatepass_ck(Map projectMap, int PROJECT_CODE) throws DataAccessException {
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		projectVO.setMEMBER_ID((String)projectMap.get("MEMBER_ID"));
		projectVO.setPASS_CK((String)projectMap.get("PASS_CK"));
		System.out.println("pass : "+projectVO.getPASS_CK());
		
		sqlSession.update("mapper.project.updatememberpass_ck",projectVO);
		
	}

	@Override
	public void updateapply_ck(Map projectMap, int PROJECT_CODE) throws DataAccessException {
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		projectVO.setMEMBER_ID((String)projectMap.get("MEMBER_ID"));
		projectVO.setPASS_CK((String)projectMap.get("APPLY_CK"));
		sqlSession.delete("mapper.project.updatememberapply_ck",projectVO);
	}

	@Override
	public List selecttag_first() throws DataAccessException {
		List<tagVO> tag_firstList =null;
		tag_firstList = sqlSession.selectList("mapper.tag.selecttag_firstList");
		return tag_firstList;
	}

	@Override
	public List selecttag_second(String TAG_FIRST) throws DataAccessException {
		List<tagVO> tag_secondList =null;
		tagVO.setTAG_FIRST(TAG_FIRST);
		System.out.println("VO확인" +tagVO.getTAG_FIRST());
		tag_secondList =sqlSession.selectList("mapper.tag.selecttag_secondList",tagVO);
		return tag_secondList;
	}

	@Override
	public List selecttag_third(String TAG_SECOND) throws DataAccessException {
		List<tagVO> tag_thirdList = null;
		tagVO.setTAG_SECOND(TAG_SECOND);
		
		tag_thirdList =sqlSession.selectList("mapper.tag.selecttag_thirdList", tagVO);
		return tag_thirdList;
	}

	@Override
	public void removefile(int PROJECT_CODE) throws DataAccessException {
		projectVO.setPROJECT_CODE(PROJECT_CODE);
		sqlSession.update("mapper.project.updateremovefile", projectVO);
		
	}

	@Override
	public List selectAllprojecttagList() throws DataAccessException {
		// TODO Auto-generated method stub
		List<tagVO> projectALLtagList = sqlSession.selectList("mapper.project.selectprojecttag");
		return projectALLtagList;
	}

}

