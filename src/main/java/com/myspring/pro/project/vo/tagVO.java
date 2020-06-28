package com.myspring.pro.project.vo;

import org.springframework.stereotype.Component;

@Component("tagVO")
public class tagVO {
	private String TAG_FIRST;
	private String TAG_SECOND;
	private String TAG_THIRD;
	
public tagVO() {
	 
}

public String getTAG_FIRST() {
	 return TAG_FIRST;
}
public void setTAG_FIRST(String TAG_FIRST) {
	 this.TAG_FIRST=TAG_FIRST;
}
public String getTAG_SECOND() {
	 return TAG_SECOND;
}
public void setTAG_SECOND(String TAG_SECOND) {
	 this.TAG_SECOND=TAG_SECOND;
}
public String getTAG_THIRD() {
	 return TAG_THIRD;
}
public void setTAG_THIRD(String TAG_THIRD) {
	 this.TAG_THIRD=TAG_THIRD;
}
}