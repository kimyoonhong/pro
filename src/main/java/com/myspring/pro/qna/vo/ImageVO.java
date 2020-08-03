package com.myspring.pro.qna.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

public class ImageVO {
	private int IMAGEFILENAMENO;
	private String IMAGEFILENAME;
	private Date regDate;
	private int QNA_ARTICLENO;
	public int getIMAGEFILENAMENO() {
		return IMAGEFILENAMENO;
	}
	public void setIMAGEFILENAMENO(int iMAGEFILENAMENO) {
		IMAGEFILENAMENO = iMAGEFILENAMENO;
	}
	public String getIMAGEFILENAME() {
		return IMAGEFILENAME;
	}
	public void setIMAGEFILENAME(String iMAGEFILENAME) {
		IMAGEFILENAME = iMAGEFILENAME;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getQNA_ARTICLENO() {
		return QNA_ARTICLENO;
	}
	public void setQNA_ARTICLENO(int qNA_ARTICLENO) {
		QNA_ARTICLENO = qNA_ARTICLENO;
	}
	
	

}
