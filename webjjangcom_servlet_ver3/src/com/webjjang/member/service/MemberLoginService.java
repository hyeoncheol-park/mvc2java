package com.webjjang.member.service;


import com.webjjang.main.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.dto.LoginDTO;


public class MemberLoginService implements Service {

	
	private MemberDAO dao;
	//파라메터를 이용해서 생성하면서 dao 를 넣어준다 -> Beans에서 처리
	public MemberLoginService(MemberDAO dao) {
		this.dao=dao;
	}
	@Override
	public LoginDTO service(Object[] objs) throws Exception {
		// TODO Auto-generated method stub
		LoginDTO dto = (LoginDTO) objs[0];
		return dao.login(dto);
	}

}
