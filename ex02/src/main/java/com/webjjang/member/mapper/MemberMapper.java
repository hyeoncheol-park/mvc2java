package com.webjjang.member.mapper;

import java.util.List;

import com.webjjang.member.dto.MemberDTO;
import com.webjjang.member.dto.LoginDTO;
import com.webjjang.util.page.PageObject;

// SQL은 패키지에 맞는 resources 안에 패키지에 맞는 폴더를 만들어서 MemberMapper.xml만들고
// 각각의 메서드에 맞는 태그를 작성해준다. 이때 아읻가 메서드 이름과 같아야한다.
public interface MemberMapper {

	//1.리스트 - list()
	public List<MemberDTO> list(PageObject pageObject);
	public Integer getTotalRow(PageObject pageObject);
	//2.회원가입 처리 - write(dto)
	public Integer write(MemberDTO dto);
	//3.회원정보보기 - view(no) / increaseHit(no)
	public MemberDTO view(int no);
	public Integer increaseHit(int no);
	//4.회원정보  수정 처리 - update(dto)
	public Integer update(MemberDTO dto);
	//5.회원탈퇴 - delete(dto) ==> no와 pw:post 방식
	public Integer delete(MemberDTO dto);

	//6.로그인 처리
	public LoginDTO login(LoginDTO dto);

	
}
