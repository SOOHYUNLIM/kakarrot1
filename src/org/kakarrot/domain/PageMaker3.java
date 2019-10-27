package org.kakarrot.domain;

import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.*;


@Getter
@ToString
public class PageMaker3 {

	private Paging paging;
	private int start, end, total;
	private boolean prev, next;
	
	public PageMaker3(int total, Paging paging) {
		
		this.total = total;
		this.paging = paging;
		int tmpEnd = paging.getPage()+2;
		
		int realEnd = (int)ceil(total/(double)paging.getAmount());

		if(paging.getPage() <3 ) {
			tmpEnd = tmpEnd+(paging.getPage()%2+1);
			this.start = 1;
		} 
		else if( paging.getPage() > realEnd-2 ) {
			this.start = realEnd-4;
		}
		else {
			this.start =  paging.getPage()-2;
		}
//		this.start = paging.getPage() < 3  ? tmpEnd = tmpEnd+(paging.getPage()%2+1) : paging.getPage()-2 ;
		this.end = realEnd > tmpEnd ? tmpEnd : realEnd ;
		this.prev = start != 1 ? true : false ;
		this.next = tmpEnd < realEnd ? true : false ;
		
	}
	
}
