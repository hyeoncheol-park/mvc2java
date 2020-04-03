package com.webjjang.member.dto;

import java.util.Date;

import lombok.Data;

@Data
// getter,setter , 생성자,toString() 자동생성
public class MemberDTO {

	private int no;
	private String title,content,writer;
	private Date writeDate;
	private int hit,reply_cnt;
	private String pw;
}
