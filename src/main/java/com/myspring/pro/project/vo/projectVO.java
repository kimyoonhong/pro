package com.myspring.pro.project.vo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("projectVO")
public class projectVO {
	private String MEMBER_ID;  
	private int PROJECT_CODE; 
	private String PROJECT_TITTLE; 
	private String PROJECT_CONTENT; 
	private String PROJECT_REGISTER_DAY; 
	private String INVITE_STRDAY; 
	private String PROJECT_FILENAME;
	private String INVITE_ENDDAY; 
	private String PROJECT_STRDAY; 
 	private String PROJECT_ENDDAY; 
	private String TOTALMEMBER;
	private String APPLY_CK;
	private int APPLYMEMBER; 
	private int PASSMEMBER; 
	private String PROJECT_TAG; 
	private String Tag1;
	private String Tag2;
	private String Tag3;
	private String Tag4;
	private String Tag5;
	private String keyword;
	private String PROJECT_STATE; // 프로젝트 상태
	
	public String getPROJECT_STATE() {
		return PROJECT_STATE;
	}
	public void setPROJECT_STATE(String pROJECT_STATE) {
		PROJECT_STATE = pROJECT_STATE;
	}

public projectVO() {
	 
 }
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public String getPROJECT_FILENAME() {
	return PROJECT_FILENAME;
}
public void setPROJECT_FILENAME(String pROJECT_FILENAME) {
	PROJECT_FILENAME = pROJECT_FILENAME;
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

public String getAPPLY_CK() {
	return APPLY_CK;
}
public void setAPPLY_CK(String aPPLY_CK) {
	APPLY_CK = aPPLY_CK;
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
 public int getPROJECT_CODE() {
	 return PROJECT_CODE;
 }
 public void setPROJECT_CODE(int PROJECT_CODE) {
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
 //asdsadas
 //asdihsakdjashkdjhsakjdhakdl
 //asjkdalksjdsakljdsakljda
 //asdsadasdasdasdada
 //asdsadsa
 public String getPROJECT_TAG() {
	 return PROJECT_TAG;
 }
 public void setPROJECT_TAG(String T_PROJECT_TAG) {
	 this.PROJECT_TAG=T_PROJECT_TAG;
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
