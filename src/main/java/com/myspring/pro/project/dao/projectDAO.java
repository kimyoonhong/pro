package com.myspring.pro.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.project.vo.projectVO;

public interface projectDAO {
	//전체 조회
	public List selectAllprojectList() throws DataAccessException;
	//프로젝트 테그 
	public List selectAllprojecttagList() throws DataAccessException;
	//프로젝트테그조회
	public List selectprojectTagList(int PROJECT_CODE) throws DataAccessException;
	//프로젝트 업데이트
	public void updateproject(Map projectMap) throws DataAccessException;
	//상세 조회
	public projectVO selectviewprojectList(int PROJECT_CODE) throws DataAccessException;
	//테그조회
	public List selectTagList() throws DataAccessException;
	 //프로젝트 생성
	public int insertproject(Map projectMap ,List<String> tag) throws DataAccessException ;
	 //프로젝트 생성시 테그생성
	public void insertprojecttaglist(Map projectMap,List<String> tag) throws DataAccessException;
	//테그 유무 확인
	public boolean selectoverlappedtag(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	public void deleteproject(int PROJECT_CODE) throws DataAccessException;
	
	//마이페이지에 들어가는 맴버별 프로젝트
	public String selectoverlappedmemberproject(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	public void insetmemberproject(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	//프로젝트 신청한 맴버 합격 불합격 보류 선택
	public void updatepass_ck(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	//프로젝트 신청한 맴버 확인
	public List selectmemberprojectlist(int PROJECT_CODE)throws DataAccessException;
	//프로젝트 관심등록한사람 다시 신청하기
	public void updateapply_ck(Map projectMap,int RPOJECT_CODE) throws DataAccessException;
	//프로젝트 수정시 테그생성
	public void insetprojecttag(Map projectMap,int PROJECT_CODE) throws DataAccessException;
	
	public void removetag(int PROJECT_CODE,String tag) throws DataAccessException;
	
	public void removefile(int PROJECT_CODE) throws DataAccessException;
	
	public int selectprojectcode() throws DataAccessException;
	
	public List<String> selectKeywordSearch(String keyword,List<String> tag) throws DataAccessException;
	
	public List selecttag_first() throws DataAccessException;
	
	public List selecttag_second(String TAG_FIRST) throws DataAccessException;
	
	public List selecttag_third(String TAG_SECOND) throws DataAccessException;


}
