package org.kakarrot.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private long bno;
	private String title,content,writer;
	private Date regdate,updatedate;
	private int total,notice;
	
	private List<String> fnames;
	
	public BoardVO() {
		fnames = new ArrayList<>();
		notice = 0;
	}
	
	public void addFileName(String fname) {
		fnames.add(fname);
	}
}
