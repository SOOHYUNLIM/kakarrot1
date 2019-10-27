package org.kakarrot.domain;

import lombok.ToString;

@ToString
public class SearchPaging extends Paging {
	private String category, value;
	
	public SearchPaging(String pageStr, String amountStr) {
		super(pageStr, amountStr);
		this.category = "1";
		this.value = "1";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null || category.equals("") ? "1" : category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null || value.equals("") ? "1" : value;
	}
	
	
}
