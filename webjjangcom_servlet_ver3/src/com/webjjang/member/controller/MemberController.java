package com.webjjang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.webjjang.main.Controller;
import com.webjjang.main.Execute;
import com.webjjang.main.Service;
import com.webjjang.util.page.PageObject;
 
//static 메서드인 경우 static import를 사용해서 메서드 까지 지정을 해놓으면 메서드만 사용할수 있다.


public class MemberController implements Controller {

	
	private Service viewService; 
	private Service writeService; 
	private Service updateService; 
	private Service deleteService; 
	private Service listService;
	
	//생성자를 이용해서 service di 적용 --> beans 에서 생성후 넣어준다.
	public MemberController(Service listService,Service viewService,
			Service writeService,Service updateService,Service deleteService
			) {
		// TODO Auto-generated constructor stub
	
		
		
	this.listService = listService; // 회원 리스트
	this.viewService = viewService; // 내정보 보기
	this.writeService = writeService; // 회원가입
	this.updateService = updateService; // 내정보수정
	this.deleteService = deleteService; // 회원탈퇴
	
	}
	
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response, String uri) throws Exception {
		// TODO Auto-generated method stub
    //고통으로 사용되는 변수
		String jsp = "";
		PageObject pageObject = PageObject.getInstance(request.getParameter("page"),
				request.getParameter("perPageNum"));
//		pageObject 
		//jsp에서  자바부분을 여기에 넣는다.
				switch (uri) {
				case "/member/list.do":						
					request.setAttribute("pageObject", pageObject);
					request.setAttribute("list", Execute.service(listService, pageObject));
					jsp="member/list";
					//frontcontrcontroller 에서 foward시킨다.
//					request.getRequestDispatcher(jsp).forward(request, response);
					break;
				case "/member/view.do":			
					int no = Integer.parseInt(request.getParameter("no"));
					int cnt = Integer.parseInt(request.getParameter("cnt"));					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					request.setAttribute("dto", Execute.service(viewService, no,cnt));
					jsp="member/view";
					//frontcontrcontroller 에서 foward시킨다.
					request.getRequestDispatcher(jsp).forward(request, response);
					break;
					//회원 가입
			case "/member/joinForm.do":			
					//JoiForm.jsp 로 바로 이동시킨다.
					jsp="member/joinForm";
					
					break;
				case "/member/delete.do":			
					
					System.out.println(deleteService);
					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					Execute.service(deleteService, Integer.parseInt(request.getParameter("no")));
					
					//자동으로 리스트로 이동시켜야 한다.
					jsp="redirect:list.do";
				
					break;
					
					
				default:
					break;
				}
				System.out.println("memberController.execute(). jsp"+jsp);
                
				return jsp;
	
	}

}
