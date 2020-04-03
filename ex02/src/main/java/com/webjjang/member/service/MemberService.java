package com.webjjang.member.service;

import java.util.List;

import com.webjjang.member.dto.LoginDTO;
import com.webjjang.member.dto.MemberDTO;
import com.webjjang.util.page.PageObject;

public interface MemberService {
	
	// 1.리스트 - list()
	public List<MemberDTO> list(PageObject pageObject);
	
	// 2.회원등록 처리 - write(dto)
	public Integer write(MemberDTO dto);
	// 3.회원 정보보기 - view(no) / increaseHit(no)
	public MemberDTO view(int no);
	
	// 4.회원 수정 처리 - update(dto)
	public Integer update(MemberDTO dto);
	// 5.회원탈퇴 - delete(dto) ==> no와 pw:post 방식
	public Integer delete(MemberDTO dto);
	//6.로그인
	public LoginDTO login(LoginDTO dto);
	

	

}
