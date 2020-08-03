package com.myspring.pro.admin.vo;

import org.springframework.stereotype.Component;

@Component("adminVO")
public class adminVO {
	private String ADMIN_ID;
	private String ADMIN_PW;
	private String ADMIN_NAME;
	private int NOTICE_CODE;
	private String NOTICE_FILENAME;
	private String NOTICE_TITLE;
	private String NOTICE_CONTENT;
	private String NOTICE_WRITEDATE;
	
	public adminVO() {
		
	}

	public String getADMIN_ID() {
		return ADMIN_ID;
	}

	public void setADMIN_ID(String aDMIN_ID) {
		ADMIN_ID = aDMIN_ID;
	}

	public String getADMIN_PW() {
		return ADMIN_PW;
	}

	public void setADMIN_PW(String aDMIN_PW) {
		ADMIN_PW = aDMIN_PW;
	}

	public String getADMIN_NAME() {
		return ADMIN_NAME;
	}

	public void setADMIN_NAME(String aDMIN_NAME) {
		ADMIN_NAME = aDMIN_NAME;
	}

	public int getNOTICE_CODE() {
		return NOTICE_CODE;
	}

	public void setNOTICE_CODE(int nOTICE_CODE) {
		NOTICE_CODE = nOTICE_CODE;
	}

	public String getNOTICE_FILENAME() {
		return NOTICE_FILENAME;
	}

	public void setNOTICE_FILENAME(String nOTICE_FILENAME) {
		NOTICE_FILENAME = nOTICE_FILENAME;
	}

	public String getNOTICE_TITLE() {
		return NOTICE_TITLE;
	}

	public void setNOTICE_TITLE(String nOTICE_TITLE) {
		NOTICE_TITLE = nOTICE_TITLE;
	}

	public String getNOTICE_CONTENT() {
		return NOTICE_CONTENT;
	}

	public void setNOTICE_CONTENT(String nOTICE_CONTENT) {
		NOTICE_CONTENT = nOTICE_CONTENT;
	}

	public String getNOTICE_WRITEDATE() {
		return NOTICE_WRITEDATE;
	}

	public void setNOTICE_WRITEDATE(String nOTICE_WRITEDATE) {
		NOTICE_WRITEDATE = nOTICE_WRITEDATE;
	}
	
	
}
