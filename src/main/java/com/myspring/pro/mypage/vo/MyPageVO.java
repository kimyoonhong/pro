package com.myspring.pro.mypage.vo;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("mypageVO")
public class MyPageVO {
	private String MEMBER_ID;     // 아이디
	private int PROJECT_CODE;     // 프로젝트 코드
	private String PROJECT_TITTLE;
	private String APPLY_CK;      // 신청여부
	private String APPLY_DAY;     // 신청일
	private String PASS_CK;       // 합격여부
	private String PASS_DAY;      // 합격일
	private String PROJECT_STATE; // 프로젝트 상태
	
	public String getPROJECT_STATE() {
		return PROJECT_STATE;
	}
	public void setPROJECT_STATE(String pROJECT_STATE) {
		PROJECT_STATE = pROJECT_STATE;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public int getPROJECT_CODE() {
		return PROJECT_CODE;
	}
	public void setPROJECT_CODE(int pROJECT_CODE) {
		PROJECT_CODE = pROJECT_CODE;
	}
	public String getAPPLY_CK() {
		return APPLY_CK;
	}
	public void setAPPLY_CK(String aPPLY_CK) {
		APPLY_CK = aPPLY_CK;
	}
	public String getAPPLY_DAY() {
		return APPLY_DAY;
	}
	public void setAPPLY_DAY(String aPPLY_DAY) {
		APPLY_DAY = aPPLY_DAY;
	}
	public String getPASS_CK() {
		return PASS_CK;
	}
	public void setPASS_CK(String pASS_CK) {
		PASS_CK = pASS_CK;
	}
	public String getPASS_DAY() {
		return PASS_DAY;
	}
	public void setPASS_DAY(String pASS_DAY) {
		PASS_DAY = pASS_DAY;
	}
	public String getPROJECT_TITTLE() {
		return PROJECT_TITTLE;
	}
	public void setPROJECT_TITTLE(String pROJECT_TITTLE) {
		PROJECT_TITTLE = pROJECT_TITTLE;
	}
	
	
	
}