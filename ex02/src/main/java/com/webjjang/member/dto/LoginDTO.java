package com.webjjang.member.dto;



import lombok.Data;

@Data
// getter,setter , 생성자,toString() 자동생성
public class LoginDTO {

	
	private String id,pw,name,photo;
	private int newMsgCnt,gradeNo;
	private String gradeName;
}
