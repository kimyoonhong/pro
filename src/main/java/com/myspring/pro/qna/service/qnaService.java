package com.myspring.pro.qna.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

/*import com.myspring.pro.qna.vo.Criteria;
*/
import com.myspring.pro.page.Criteria;
import com.myspring.pro.qna.vo.qnaArticleVO;
import com.myspring.pro.qna.vo.qnatagVO;

public interface qnaService {
	//게시글 목록 조회
	public List<qnaArticleVO> listArticles(Criteria cri) throws Exception;
	
	//게시글 작성
	public int addNewArticle(Map articleMap) throws Exception;
	
	//상세글 조회
	public qnaArticleVO viewArticle(int QNA_ARTICLENO) throws Exception;
	
	//게시글 삭제
	public void modArticle(Map articleMap) throws Exception;
	//게시글 삭제
	public void removeArticle(int QNA_ARTICLENO) throws Exception;
	//테그리스트 조회
	public List<qnatagVO> QtagList() throws DataAccessException;
	
	// 게시물 총 갯수
	public int listCount() throws Exception;
	
	// 게시물 목록 + 페이징
	//public List listPage(int rowStart, int rowEnd) throws Exception;
}
