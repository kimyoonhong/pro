package com.myspring.pro.qna.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

//import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro.qna.vo.qnaArticleVO;
import com.myspring.pro.qna.vo.qnatagVO;
/*import com.myspring.pro.qna.vo.Criteria;*/
import com.myspring.pro.page.Criteria;

import com.myspring.pro.qna.vo.ImageVO;


@Repository("qnaDAO")
public class qnaDAOImpl implements qnaDAO {
	@Autowired
	private SqlSession sqlSession;
	private qnaArticleVO qnaArticleVO = new qnaArticleVO();
	@Autowired
	private Criteria Criteria;
	
	@Override
	//게시글 목록 조회
	
	public List<qnaArticleVO> selectAllArticlesList(Criteria cri) throws DataAccessException {
		
		return sqlSession.selectList("mapper.qna.listPage",cri);
	}
	 
	@Override
	//새글 추가
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int QNA_ARTICLENO = selectNewArticleNO(); //새글번호
		articleMap.put("QNA_ARTICLENO", QNA_ARTICLENO); //글번호를 아티클맵에 저장
		
		System.out.println("글 제목: " + articleMap.get("QNA_TITLE"));
		System.out.println("글 테그: " + articleMap.get("QNA_TAG"));
		System.out.println("글 번호: " + articleMap.get("QNA_ARTICLENO"));
		
		sqlSession.insert("mapper.qna.insertNewArticle",articleMap); //인설트 문 호출
		return QNA_ARTICLENO;
	}
	
	
	@Override
	//게시글 상세 조회
	public qnaArticleVO selectArticle(int QNA_ARTICLENO) throws DataAccessException {
		return sqlSession.selectOne("mapper.qna.selectArticle", QNA_ARTICLENO);
	}

	@Override
	//수정
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.qna.updateArticle", articleMap);
	}

	@Override
	//삭제
	public void deleteArticle(int QNA_ARTICLENO) throws DataAccessException {
		sqlSession.delete("mapper.qna.deleteArticle", QNA_ARTICLENO);
		
	}
	//새글 추가 메서드
	private int selectNewArticleNO() throws DataAccessException {
		int b;
		if(sqlSession.selectOne("mapper.qna.selectNewArticleNO")==null) {
			b=1;
		}else {
			b=sqlSession.selectOne("mapper.qna.selectNewArticleNO");
		}
		return b;
	}
	//이미지파일업로드
	@Override
	public List selectImageFileList(int QNA_ARTICLENO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.qna.selectImageFileList",QNA_ARTICLENO);
		return imageFileList;
	}
	//이미지파일 업로드
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.qna.selectNewImageFileNO");
	}

	//QNA테그리스트 소환
	public List<qnatagVO> selectQTagList() throws DataAccessException {
		List<qnatagVO> qnaTagList = null;
		qnaTagList = sqlSession.selectList("mapper.qnatag.selectqnaTagList");
		return qnaTagList;
	}
	
	// 게시물 총 갯수
	@Override
	public int listCount() throws Exception {
	 return sqlSession.selectOne("mapper.qna.listCount"); 
	}
	

}
