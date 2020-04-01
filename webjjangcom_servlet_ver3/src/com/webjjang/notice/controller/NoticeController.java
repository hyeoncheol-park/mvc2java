package com.webjjang.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.webjjang.main.Controller;
import com.webjjang.main.Execute;
import com.webjjang.main.Service;
import com.webjjang.notice.dto.NoticeDTO;
import com.webjjang.util.page.PageObject;
 
//static 메서드인 경우 static import를 사용해서 메서드 까지 지정을 해놓으면 메서드만 사용할수 있다.


public class NoticeController implements Controller {

	private Service listService; 
	private Service viewService; 
	private Service writeService; 
	private Service updateService; 
	private Service deleteService; 
	
	//생성자를 이용해서 service di 적용 --> beans 에서 생성후 넣어준다.
	public NoticeController(Service listService,Service viewService,
			Service writeService,Service updateService,Service deleteService) {
		// TODO Auto-generated constructor stub
	
		
		
	this.listService = listService;
	this.viewService = viewService;
	this.writeService = writeService;
	this.updateService = updateService;
	this.deleteService = deleteService;
	
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
				case "/notice/list.do":						
					request.setAttribute("pageObject", pageObject);
					request.setAttribute("list", Execute.service(listService, pageObject));
					jsp="notice/list";
					//frontcontrcontroller 에서 foward시킨다.
//					request.getRequestDispatcher(jsp).forward(request, response);
					break;
				case "/notice/view.do":			
					int no = Integer.parseInt(request.getParameter("no"));
					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					request.setAttribute("dto", Execute.service(viewService, no));
					jsp="notice/view";
					//frontcontrcontroller 에서 foward시킨다.
//					request.getRequestDispatcher(jsp).forward(request, response);
					break;
				case "/notice/writeForm.do":			
					
//					writeForm .jsp 바로 이동 ㅅㅣ킨다.
					jsp="notice/writeForm";
					
					break;
				case "/notice/write.do":			
					Execute.service(writeService, getDTO(request.getParameter("title"),request.getParameter("content"), 
							request.getParameter("startDate"), request.getParameter("endDate")));
					jsp="redirect:list.do";
					
						
					break;
				case "/notice/delete.do":			
					
					System.out.println(deleteService);
					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					Execute.service(deleteService, Integer.parseInt(request.getParameter("no")));
					
					//자동으로 리스트로 이동시켜야 한다.
					jsp="redirect:list.do";
				
					break;
					
					
				default:
					break;
				}
				System.out.println("noticeController.execute(). jsp"+jsp);
                
				return jsp;
	
	}

	
	public NoticeDTO getDTO(String title, String content , String startDate,String endDate) {
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setStartDate(startDate);
	    dto.setEndDate(endDate);
		
		return dto;
	}
}
