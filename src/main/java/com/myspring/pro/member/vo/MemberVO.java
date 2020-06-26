package com.myspring.pro.member.vo;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String MEMBER_ID; // 아이디
	private String MEMBER_PW; // 비밀번호
	private String MEMBER_NAME; // 이름
	private String MEMBER_GENDER;// 성별
	private String MEMBER_BIRTH_Y; // 생년월일 년
	private String MEMBER_BIRTH_M; // 생년월일 월
	private String MEMBER_BIRTH_D; // 생년월일 일
	private String MEMBER_CONTENT; // 자기소개
	private String MEMBER_JOB; // 전공
	
	private String TEL1; // 전화번호 1
	private String TEL2; // 전화번호 2
	private String TEL3; // 전화번호 3
	private String HP1;  // 휴대전화 1
	private String HP2;  // 휴대전화 2
	public String getEMAIL2() {
		return EMAIL2;
	}
	public void setEMAIL2(String eMAIL2) {
		EMAIL2 = eMAIL2;
	}
	private String HP3;  // 휴대전화 3
	private String SMSSTS_YN; // SMS 수신동의
	private String EMAIL1;  // 이메일 1
	private String EMAIL2;  // 이메일 2
	private String EMAILSTS_YN; // 이메일 수신 동의
	private String ZIPCODE; // 우편번호
	private String ROADADDRESS; // 도로명 주소
	private String JIBUNADDRESS; // 지번 주소
	private String NAMUJIADDRESS; // 나머지 주소
	private Date JOINDATE; // 회원가입 일자
	
	
	public String getTEL2() {
		return TEL2;
	}
	public void setTEL2(String tEL2) {
		TEL2 = tEL2;
	}
	public String getTEL3() {
		return TEL3;
	}
	public void setTEL3(String tEL3) {
		TEL3 = tEL3;
	}
	public String getHP2() {
		return HP2;
	}
	public void setHP2(String hP2) {
		HP2 = hP2;
	}
	public String getHP3() {
		return HP3;
	}
	public void setHP3(String hP3) {
		HP3 = hP3;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public String getMEMBER_GENDER() {
		return MEMBER_GENDER;
	}
	public void setMEMBER_GENDER(String mEMBER_GENDER) {
		MEMBER_GENDER = mEMBER_GENDER;
	}
	public String getTEL1() {
		return TEL1;
	}
	public void setTEL1(String tEL1) {
		TEL1 = tEL1;
	}
	public String getHP1() {
		return HP1;
	}
	public void setHP1(String hP1) {
		HP1 = hP1;
	}
	public String getSMSSTS_YN() {
		return SMSSTS_YN;
	}
	public void setSMSSTS_YN(String sMSSTS_YN) {
		SMSSTS_YN = sMSSTS_YN;
	}
	public String getEMAIL1() {
		return EMAIL1;
	}
	public void setEMAIL1(String eMAIL1) {
		EMAIL1 = eMAIL1;
	}
	public String getEMAILSTS_YN() {
		return EMAILSTS_YN;
	}
	public void setEMAILSTS_YN(String eMAILSTS_YN) {
		EMAILSTS_YN = eMAILSTS_YN;
	}
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public String getROADADDRESS() {
		return ROADADDRESS;
	}
	public void setROADADDRESS(String rOADADDRESS) {
		ROADADDRESS = rOADADDRESS;
	}
	public String getJIBUNADDRESS() {
		return JIBUNADDRESS;
	}
	public void setJIBUNADDRESS(String jIBUNADDRESS) {
		JIBUNADDRESS = jIBUNADDRESS;
	}
	public String getNAMUJIADDRESS() {
		return NAMUJIADDRESS;
	}
	public void setNAMUJIADDRESS(String nAMUJIADDRESS) {
		NAMUJIADDRESS = nAMUJIADDRESS;
	}
	public String getMEMBER_BIRTH_Y() {
		return MEMBER_BIRTH_Y;
	}
	public void setMEMBER_BIRTH_Y(String mEMBER_BIRTH_Y) {
		MEMBER_BIRTH_Y = mEMBER_BIRTH_Y;
	}
	public String getMEMBER_BIRTH_M() {
		return MEMBER_BIRTH_M;
	}
	public void setMEMBER_BIRTH_M(String mEMBER_BIRTH_M) {
		MEMBER_BIRTH_M = mEMBER_BIRTH_M;
	}
	public String getMEMBER_BIRTH_D() {
		return MEMBER_BIRTH_D;
	}
	public void setMEMBER_BIRTH_D(String mEMBER_BIRTH_D) {
		MEMBER_BIRTH_D = mEMBER_BIRTH_D;
	}
	public String getMEMBER_CONTENT() {
		return MEMBER_CONTENT;
	}
	public void setMEMBER_CONTENT(String mEMBER_CONTENT) {
		MEMBER_CONTENT = mEMBER_CONTENT;
	}
	public String getMEMBER_JOB() {
		return MEMBER_JOB;
	}
	public void setMEMBER_JOB(String mEMBER_JOB) {
		MEMBER_JOB = mEMBER_JOB;
	}
	public Date getJOINDATE() {
		return JOINDATE;
	}
	public void setJOINDATE(Date jOINDATE) {
		JOINDATE = jOINDATE;
	}
	
	
}