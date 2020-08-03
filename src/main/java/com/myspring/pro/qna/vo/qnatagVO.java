package com.myspring.pro.qna.vo;

import org.springframework.stereotype.Component;

@Component("qnatagVO")
public class qnatagVO {
	private int QNA_NO;
	private String QNA_TAG;
	
public qnatagVO() {
	 
}

public String getQNA_TAG() {
	return QNA_TAG;
}

public void setQNA_TAG(String qNA_TAG) {
	QNA_TAG = qNA_TAG;
}

public int getQNA_NO() {
	return QNA_NO;
}

public void setQNA_NO(int qNA_NO) {
	QNA_NO = qNA_NO;
}


}