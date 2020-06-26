package com.myspring.pro.project.vo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("projectVO")
public class projectVO {
	private String MEMBER_ID;  // 회원아이디
	private String PROJECT_CODE; // 프로젝트 코드
	private String PROJECT_TITTLE; // 프로젝트 제목
	private String PROJECT_CONTENT; // 프로젝트 내용
	private String PROJECT_REGISTER_DAY; // 등록일
	private String INVITE_STRDAY; // 모집일
	private String INVITE_ENDDAY; // 모집마감일
	private String PROJECT_STRDAY; // 프로젝트 시작일
 	private String PROJECT_ENDDAY; // 프로젝트 종료일
	private String TOTALMEMBER; // 총원
	private int APPLYMEMBER; // 현재 지원 한 인원
	private int PASSMEMBER; // 현재 모집 한 인원
	private String T_PROJECT_TAG; // 프로젝트 태그


 public projectVO() {
 }
 
 public String getMEMBER_ID() {
	 return MEMBER_ID;
 }
 public void setMEMBER_ID(String MEMBER_ID) {
	 this.MEMBER_ID=MEMBER_ID;
 }
 
 public String getPROJECT_CODE() {
	 return PROJECT_CODE;
 }
 public void setPROJECT_CODE(String PROJECT_CODE) {
	 this.PROJECT_CODE=PROJECT_CODE;
 }
 
 public String getPROJECT_TITTLE() {
	 return PROJECT_TITTLE;
 }
 public void setPROJECT_TITTLE(String PROJECT_TITTLE) {
	 this.PROJECT_TITTLE=PROJECT_TITTLE;
 }
 
 public String getPROJECT_CONTENT() {
	 return PROJECT_CONTENT;
 }
 public void setPROJECT_CONTENT(String PROJECT_CONTENT) {
	 this.PROJECT_CONTENT=PROJECT_CONTENT;
 }
 
 public String getPROJECT_REGISTER_DAY() {
	 return PROJECT_REGISTER_DAY;
 }
 public void setPROJECT_REGISTER_DAY(String PROJECT_REGISTER_DAY) {
	 this.PROJECT_REGISTER_DAY=PROJECT_REGISTER_DAY;
 }
 
 public String getINVITE_STRDAY() {
	 return INVITE_STRDAY;
 }
 public void setINVITE_STRDAY(String INVITE_STRDAY) {
	 this.INVITE_STRDAY=INVITE_STRDAY;
 }
 
 public String getINVITE_ENDDAY() {
	 return INVITE_ENDDAY;
 }
 public void setINVITE_ENDDAY(String INVITE_ENDDAY) {
	 this.INVITE_ENDDAY=INVITE_ENDDAY;
 }
 
 public String getPROJECT_STRDAY() {
	 return PROJECT_STRDAY;
 }
 public void setPROJECT_STRDAY(String PROJECT_STRDAY) {
	 this.PROJECT_STRDAY=PROJECT_STRDAY;
 }
 
 public String getPROJECT_ENDDAY() {
	 return PROJECT_ENDDAY;
 }
 public void setPROJECT_ENDDAY(String PROJECT_ENDDAY) {
	 this.PROJECT_ENDDAY=PROJECT_ENDDAY;
 }
 
 public String getTOTALMEMBER() {
	 return TOTALMEMBER;
 }
 public void setTOTALMEMBER(String TOTALMEMBER) {
	 this.TOTALMEMBER=TOTALMEMBER;
 }
 public String getT_PROJECT_TAG() {
	 return T_PROJECT_TAG;
 }
 public void setT_PROJECT_TAG(String T_PROJECT_TAG) {
	 this.T_PROJECT_TAG=T_PROJECT_TAG;
 }
 
 public int getAPPLYMEMBER() {
	 return APPLYMEMBER;
 }
 public void setAPPLYMEMBER(int APPLYMEMBER) {
	 this.APPLYMEMBER=APPLYMEMBER;
 }
 
 public int getPASSMEMBER() {
	 return PASSMEMBER;
 }
 public void setPASSMEMBER(int PASSMEMBER) {
	 this.PASSMEMBER=PASSMEMBER;
 }
 
 
 
}