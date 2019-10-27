package org.kakarrot.domain;

import lombok.ToString;
import lombok.extern.log4j.Log4j;

@ToString
@Log4j
public class Paging {
	private int page, amount;

	public Paging() {
		this.page = 1;
		this.amount = 10;
	}
	
	public Paging(String pageStr, String amountStr) {
		try {
			this.page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			this.page = 1;
		}
		
		try {
			this.amount = Integer.parseInt(amountStr);
		} catch (Exception e) {
			this.amount = 10;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			log.info("잘못된 페이지 번호");
			return;
		}
		this.page = page;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
