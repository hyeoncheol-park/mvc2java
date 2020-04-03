package com.webjjang.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.webjjang.member.dto.LoginDTO;
import com.webjjang.member.dto.MemberDTO;
import com.webjjang.member.mapper.MemberMapper;
import com.webjjang.util.page.PageObject;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 로그를 출력하기 위해서 - log.info()
@Log4j
// 자동생성  - @Controller ,@Service,@Repository,@Compoent
//  	   @RestController, @ControllerAdvice, @ RestControllerAdvice
@Service
@Qualifier("ms")
// 생성자를 이용해서 private 멤버 변수에 Di 적용 -> mapper
@AllArgsConstructor
// interface 를 상속받은 클래스라는 뜻의 Impl
public class MemberServiceImpl implements MemberService {

	// DB 처리를 위한 mapper 변수 선언
	private MemberMapper mapper;
	
	@Override
	public List<MemberDTO> list(PageObject pageObject) {
		// TODO Auto-generated method stub
		// 페이지 정보 계산 메서드 호출
		pageObject.calcuPageInfo();
		//jsp의 페이지 네이션을 위한 계산 -> jsp에 전달이 되어야 한다. request에 담는다 . (Model)
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		System.out.println("MemberController.list().pageObject:"+pageObject);
		return mapper.list(pageObject);
	}

	@Override
	public Integer write(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mapper.write(dto);
	}

	@Override
	public MemberDTO view(int no) {
		// TODO Auto-generated method stub
		// 조회수 1증가 시킨다.
		mapper.increaseHit(no);
		return mapper.view(no);
	}


	@Override
	public Integer update(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mapper.update(dto);
	}

	@Override
	public Integer delete(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mapper.delete(dto);
	}

	@Override
	public LoginDTO login(LoginDTO dto) {
		// TODO Auto-generated method stub
		return mapper.login(dto);
	}

}
