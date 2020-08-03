package com.myspring.pro.qna.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("qnaArticleVO")
public class qnaArticleVO {
	private int level;
	private int QNA_ARTICLENO;   //글번호
	private int QNA_PARENTNO;	//글계층구조 번호
	private String QNA_TAG;		//QNA테그 유형
	private String QNA_TITLE;	//QNA제목
	private String QNA_CONTENT;	//QNA글내용
	private String MEMBER_ID;	//회원아이디
	private String IMAGEFILENAME; //이미지파일 업로드
	private Date  writeDate;	//작성일
	

	public qnaArticleVO() {
		//System.out.println("qnaArticleVO 생성자");
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getQNA_ARTICLENO() {
		return QNA_ARTICLENO;
	}


	public void setQNA_ARTICLENO(int qNA_ARTICLENO) {
		QNA_ARTICLENO = qNA_ARTICLENO;
	}


	public int getQNA_PARENTNO() {
		return QNA_PARENTNO;
	}


	public String getIMAGEFILENAME() {
		return IMAGEFILENAME;
	}


	public void setIMAGEFILENAME(String iMAGEFILENAME) {
		IMAGEFILENAME = iMAGEFILENAME;
	}


	public void setQNA_PARENTNO(int qNA_PARENTNO) {
		QNA_PARENTNO = qNA_PARENTNO;
	}


	public String getQNA_TAG() {
		return QNA_TAG;
	}


	public void setQNA_TAG(String qNA_TAG) {
		QNA_TAG = qNA_TAG;
	}


	public String getQNA_TITLE() {
		return QNA_TITLE;
	}


	public void setQNA_TITLE(String qNA_TITLE) {
		QNA_TITLE = qNA_TITLE;
	}


	public String getQNA_CONTENT() {
		return QNA_CONTENT;
	}


	public void setQNA_CONTENT(String qNA_CONTENT) {
		QNA_CONTENT = qNA_CONTENT;
	}



	public String getMEMBER_ID() {
		return MEMBER_ID;
	}


	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}


	public Date getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	
}
