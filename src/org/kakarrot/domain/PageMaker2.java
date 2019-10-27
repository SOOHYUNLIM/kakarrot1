package org.kakarrot.domain;

import static java.lang.Math.*;

import lombok.Getter;

@Getter
public class PageMaker2 {
	private Paging paging;
	private int start, end, total;
	private boolean prev, next;
	
	public PageMaker2(int total, Paging paging) {
		
		this.total = total;
		this.paging = paging;
		
		int tmpEnd = (int)ceil(paging.getPage()/5.0)*5;
		
		int realEnd = total/paging.getAmount();
		
		this.start = tmpEnd-4;
		
		this.prev = start != 1 ? true :false ;
		this.next = tmpEnd < realEnd ? true : false ;
		this.end = realEnd > tmpEnd ? tmpEnd : realEnd ;
	}
}
