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
	
	@Override
	public List projectTagList(int PROJECT_CODE) throws DataAccessException {
		List<projectVO> projectTagList = null;
		projectTagList=projectDAO.selectprojectTagList(PROJECT_CODE);
		return projectTagList;
	}
/*	public int insertprojecttaglist(Map projectMap,List<String> tag) throws DataAccessException{
		return projectDAO.insertprojecttaglist(projectMap, tag);
	}*/

	
	public int addproject(Map projectMap,List<String> tag) throws DataAccessException {

		return projectDAO.insertproject(projectMap,tag);
	}

	
	public void removeproject(int PROJECT_CODE) throws DataAccessException {
		 projectDAO.deleteproject(PROJECT_CODE);
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

		@Override
		public projectVO viewproject(int PROJECT_CODE) throws DataAccessException {
			System.out.println("여기여기");
			return projectDAO.selectviewprojectList(PROJECT_CODE);
		}

		@Override
		public void modproject(Map projectMap) throws Exception {
			projectDAO.updateproject(projectMap);
		}

		@Override
		public void removetag(int PROJECT_CODE, String tag) throws DataAccessException {
			
			projectDAO.removetag(PROJECT_CODE, tag);
		}

		@Override
		public void addprojecttag(Map projectMap,int PROJECT_CODE) throws DataAccessException {
			projectDAO.insetprojecttag(projectMap,PROJECT_CODE);
		}

		@Override
		public boolean overlappedTag(Map projectMap,int PROJECT_CODE) throws DataAccessException {
			boolean a=  projectDAO.selectoverlappedtag(projectMap, PROJECT_CODE);
			return a;
		}

		@Override
		public void addmemberproject(Map projectMap, int PROJECT_CODE) throws DataAccessException {
			
			
		}

		@Override
		public boolean overlappedMemberproject(Map projectMap, int PROJECT_CODE) throws DataAccessException {
			// TODO Auto-generated method stub
			return false;
		}


		


	
	

}
