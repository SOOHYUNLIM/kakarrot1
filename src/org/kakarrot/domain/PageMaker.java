package org.kakarrot.domain;

import static java.lang.Math.*;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {
	private int start, end, total, page;
	private boolean prev, next;
	private Paging paging;
	
	public PageMaker(int total, Paging paging) {
		super();
		this.total = total;
		this.paging = paging;
		
		int tempEnd = (int)ceil(paging.getPage()/10.0)*10;
		
		this.start = tempEnd - 9;
		this.prev = this.start != 1 ? true : false;
		
		int realEnd = total/paging.getAmount()+1;
		this.end = tempEnd > realEnd ? realEnd : tempEnd;
		this.next = this.end*paging.getAmount() < total ? true : false;
	}
}
