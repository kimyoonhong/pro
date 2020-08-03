package com.myspring.pro.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro.page.Criteria;
import com.myspring.pro.qna.vo.qnaArticleVO;
import com.myspring.pro.qna.vo.qnatagVO;


public interface qnaDAO {
	//글목록 조회
	public List selectAllArticlesList(Criteria cri) throws DataAccessException;
	//글작성
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	//public void insertNewImage(Map articleMap) throws DataAccessException;
	//글상세글 조회
	public qnaArticleVO selectArticle(int QNA_ARTICLENO) throws DataAccessException;
	//수정
	public void updateArticle(Map articleMap) throws DataAccessException;
	//삭제
	public void deleteArticle(int QNA_ARTICLENO) throws DataAccessException;
	//이미지파일
	public List selectImageFileList(int QNA_ARTICLENO) throws DataAccessException;
	
	//qnatag리스트 소환
	public List<qnatagVO> selectQTagList() throws DataAccessException;
	
	// 게시물 총 갯수
	public int listCount() throws Exception;
	

}
