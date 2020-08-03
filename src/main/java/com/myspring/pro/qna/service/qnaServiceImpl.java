package com.myspring.pro.qna.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro.qna.dao.qnaDAO;
import com.myspring.pro.qna.vo.qnaArticleVO;
//import com.myspring.pro.board.vo.ImageVO;
import com.myspring.pro.qna.vo.qnatagVO;
/*import com.myspring.pro.qna.vo.Criteria;
*/
import com.myspring.pro.page.Criteria;

@Service("qnaService")
@Transactional(propagation = Propagation.REQUIRED)
public class qnaServiceImpl  implements qnaService{
	@Autowired
	qnaDAO qnaDAO;
	
	//게시목록조회
	public List<qnaArticleVO> listArticles(Criteria cri) throws Exception{
		return qnaDAO.selectAllArticlesList(cri);
      
	}
	// 게시물 총 갯수
		@Override
		public int listCount() throws Exception {
		 return qnaDAO.listCount(); 
		}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.insertNewArticle(articleMap);
	}

	@Override
	public qnaArticleVO viewArticle(int QNA_ARTICLENO) throws Exception {
		// TODO Auto-generated method stub
		qnaArticleVO qnaArticleVO = qnaDAO.selectArticle(QNA_ARTICLENO);
		return qnaArticleVO;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		qnaDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int QNA_ARTICLENO) throws Exception {
		// TODO Auto-generated method stub
		qnaDAO.deleteArticle(QNA_ARTICLENO);
	}

	//QNA테그리스트 소환
	@Override
	public List<qnatagVO> QtagList() throws DataAccessException {
		List qnaTagList = null;
		qnaTagList = qnaDAO.selectQTagList();
		
		return qnaTagList;
	}
	
	
/*
	// 게시물 목록 + 페이징
	@Override
	public List listPage(int rowStart, int rowEnd) throws Exception {
	 return qnaDAO.listPage(rowStart, rowEnd);
	}
*/
}
