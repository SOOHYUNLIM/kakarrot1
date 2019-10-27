package org.kakarrot.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String id, pw, name, email, pic;
	private Date regdate,updatedate;
	
}
