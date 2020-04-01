package com.webjjang.main;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.webjjang.main.Controller;
import com.webjjang.main.Execute;
import com.webjjang.main.Service;
import com.webjjang.util.page.PageObject;
 
//static 메서드인 경우 static import를 사용해서 메서드 까지 지정을 해놓으면 메서드만 사용할수 있다.


public class AjaxController implements Controller {
	


	
	private Service memberIdCheckService; // 회원 아이디 자동 체크 시 사용
	
	
	public void setmemberIdCheckService(Service memberIdCheckService) {
		this.memberIdCheckService=memberIdCheckService;
	}
	//생성자를 이용해서 service id 적용 --> beans 에서 생성후 넣어준다.
	public AjaxController() {}
	
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response, String uri) throws Exception {
		//서버에서 클라이언트 방향을로 데이터를 전송하기 위한 객체
        response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		switch (uri) {
				case "/ajax/idCheck.do":
					System.out.println("AjaxController.execute() - idCheck");
					// 서비스를 갔다가 DB에서 확인할 결과로 클라이트에 보낼 내용을 결정한다.
					String id = (String)memberIdCheckService.service(new Object[] {request.getParameter("id")});
					if(id == null)
						out.print("<span style='color:blue'>사용 가능한 아이디 입니다.</span>");
					else
						out.print("<span style='color:red'>중복된 아이디 입니다.</span>");
					break;
				default:
					break;
				}
                
				return null;
	
	}

}
