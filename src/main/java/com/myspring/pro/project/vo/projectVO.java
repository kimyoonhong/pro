package com.myspring.pro.project.vo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("projectVO")
public class projectVO {
	private String MEMBER_ID;  //ȸ�����̵�
	private String PROJECT_CODE; //������Ʈ�ڵ�
	private String PROJECT_TITTLE; //������Ʈ ����
	private String PROJECT_CONTENT; // ����
	private String PROJECT_REGISTER_DAY; //�ۼ���
	private String INVITE_STRDAY; // ���� ������
	private String INVITE_ENDDAY; // ���� ������
	private String PROJECT_STRDAY; // ������Ʈ ������
 	private String PROJECT_ENDDAY; // ������Ʈ ������
	private String TOTALMEMBER; //�ѿ�
	private int APPLYMEMBER; //������ �� 
	private int PASSMEMBER; //������ �ο���
	private String T_PROJECT_TAG; //������Ʈ �ױ�
	private String Tag1;
	private String Tag2;
	private String Tag3;
	private String Tag4;
	
	private String Tag5;
	private String keyword;

public projectVO() {
	 
 }
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public projectVO(String tag1,String tag2,String tag3,String tag4,String tag5) {
	this.Tag1 = tag1;
	this.Tag2 = tag2;
	this.Tag3 = tag3;
	this.Tag4 = tag4;
	this.Tag5 = tag5;
}

public void setTags(String tag1,String tag2,String tag3,String tag4,String tag5) {
	this.Tag1 = tag1;
	this.Tag2 = tag2;
	this.Tag3 = tag3;
	this.Tag4 = tag4;
	this.Tag5 = tag5;
}
public String getTag1() {
	return Tag1;
}
public void setTag1(String tag1) {
}
public String getTag2() {
	return Tag2;
}
public void setTag2(String tag2) {
	this.Tag2 = tag2;
}
public String getTag3() {
	return Tag3;
}
public void setTag3(String tag3) {
	this.Tag3 = tag3;
}
public String getTag4() {
	return Tag4;
}
public void setTag4(String tag4) {
	this.Tag4 = tag4;
}
public String getTag5() {
	return Tag5;
}
public void setTag5(String tag5) {
	this.Tag5 = tag5;
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
