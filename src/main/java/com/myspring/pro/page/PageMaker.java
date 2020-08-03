package com.myspring.pro.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Repository("PageMaker")
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	@Autowired
	private Criteria cri = new Criteria();
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public Criteria getCri() {
		return cri;
	}
	 
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
	  
		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	public String nextQuery(int page) {
		
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
						    .queryParam("page", page+11)
							.queryParam("perPageNum",cri.getPerPageNum()+11)
							.build();
		/*
		 * System.out.println("page"+page);
		 * System.out.println("cri.getPerPageNum()"+cri.getPerPageNum());
		 */
		return uriComponents.toUriString();
	}
	
public String backQuery(int page) {
		
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
						    .queryParam("page", page-11)
							.queryParam("perPageNum",cri.getPerPageNum()-11)
							.build();
		/*
		 * System.out.println("page"+page);
		 * System.out.println("cri.getPerPageNum()"+cri.getPerPageNum());
		 */
		return uriComponents.toUriString();
	}
public String makeQuery(int page) {
	UriComponents uriComponents =
	UriComponentsBuilder.newInstance()
					    .queryParam("page", page)
						.queryParam("perPageNum", cri.getPerPageNum())
						.build();
	   
	return uriComponents.toUriString();
}
}