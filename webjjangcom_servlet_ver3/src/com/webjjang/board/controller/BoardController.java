package com.webjjang.board.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.board.dto.BoardDTO;
import com.webjjang.board.dto.BoardReplyDTO;
import com.webjjang.main.Controller;
import com.webjjang.main.Execute;
import com.webjjang.main.Service;
import com.webjjang.util.page.PageObject;
 
//static 메서드인 경우 static import를 사용해서 메서드 까지 지정을 해놓으면 메서드만 사용할수 있다.


public class BoardController implements Controller {

	private Service listService; 
	private Service viewService; 
	private Service writeService; 
	private Service updateService; 
	private Service deleteService; 
	//댓글
	private Service replyListService;
	private Service replyWriteService;
	private Service replyUpdateService;
	private Service replyDeleteService;
	
	//생성자를 이용해서 service ID 적용 --> Beans 에서 생성후 넣어준다.
	public BoardController(Service listService,Service viewService,
			Service writeService,Service updateService,Service deleteService,
			Service replyListService,
			Service replyWriteService,
			Service replyUpdateService,
			Service replyDeleteService
			
			) {
		// TODO Auto-generated constructor stub
	
		
		
	this.listService = listService;
	this.viewService = viewService;
	this.writeService = writeService;
	this.updateService = updateService;
	this.deleteService = deleteService;
	
	//댓글서비스
	this.replyListService = replyListService;
	this.replyWriteService = replyWriteService;
	this.replyUpdateService = replyUpdateService;
	this.replyDeleteService = replyDeleteService;
	
	}
	
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response, String uri) throws Exception {
		// TODO Auto-generated method stub
    //고통으로 사용되는 변수
		String jsp = "";
		PageObject pageObject = PageObject.getInstance(request.getParameter("page"),
				request.getParameter("perPageNum"));
		
		//검색에 대한 데이터 셋팅
	String key = request.getParameter("key");	
	String word = request.getParameter("word");
	if(word != null) {
		pageObject.setKey(key);
		pageObject.setWord(word);
	}
		
//		pageObject 
		//jsp에서  자바부분을 여기에 넣는다.
				switch (uri) {
				case "/board/list.do":						
					request.setAttribute("pageObject", pageObject);
					request.setAttribute("list", Execute.service(listService, pageObject));
					jsp="board/list";
					//frontcontrcontroller 에서 foward시킨다.
//					request.getRequestDispatcher(jsp).forward(request, response);
					break;
					//게시판 글보기 + 댓글 리스트 보기  추가 (별도의 데이터를 받아와서 request 에 넣는다
				case "/board/view.do":			
					int no = Integer.parseInt(request.getParameter("no"));
					int cnt = Integer.parseInt(request.getParameter("cnt"));
					//게시판 글보기 데이터 가졍괴
					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					request.setAttribute("dto", Execute.service(viewService, no,cnt));

					//댓글 리스트 데이터 가져오기
					request.setAttribute("replyList", Execute.service(replyListService, no,cnt));
					
		            jsp="board/view";
					//frontcontrcontroller 에서 foward시킨다.
//					request.getRequestDispatcher(jsp).forward(request, response);
					break;
				case "/board/writeForm.do":			
					
					jsp="board/writeForm";
					break;
				
				case "/board/write.do":			
					BoardDTO dto = new BoardDTO();

					Execute.service(writeService,
							getDTO(request.getParameter("title"),
									request.getParameter("content"), request.getParameter("writer")));
					System.out.println("111111111111111111111111111111111111111111111111111111111111"+dto.getTitle());
					jsp="redirect:list.do?page=1&perPageNum=";
					break;
				case "/board/updateForm.do":
					
					no = Integer.parseInt(request.getParameter("no"));
					request.setAttribute("dto", Execute.service(viewService, no,0));
				    jsp= "board/updateForm";
				    break;
				case "/board/update.do":
					// 데이터를 받아 dto를 만든 후 서비스에 전달해서 DB에 글쓰기를 한다.
					no = Integer.parseInt(request.getParameter("no"));
					Execute.service(updateService, 
							getDTO(no,
								request.getParameter("title"),
								request.getParameter("content"), request.getParameter("writer")));
					// 자동으로 리스트로 이동시켜야 한다.
					jsp ="redirect:view.do?no=" + no 
							+ "&cnt=0"
							+ "&page=" + pageObject.getPage()
							+ "&perPageNum=" + pageObject.getPerPageNum()
							+ ((pageObject.getWord() != null && !pageObject.getWord().equals(""))
							  ? "&key="+pageObject.getKey() 
							  	+ "&word=" 
							  	// 검색어가 한글이 경우 엔코딩해서 넘긴다.
							    + URLEncoder.encode(pageObject.getWord(),"utf-8"):"");
					System.out.println("BoardController.execute().jsp:11"+jsp);
					break;
				
				case "/board/delete.do":			
					
					System.out.println(deleteService);
					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					Execute.service(deleteService, Integer.parseInt(request.getParameter("no")));
					
					//자동으로 리스트로 이동시켜야 한다.
					jsp="redirect:list.do";
				
					break;
				case "/board/replyDelete.do":			
					
					System.out.println(replyDeleteService);
					//request에 싱행한 결과값을 저장 -> jsp에서 꺼낸 쓴다.
					Execute.service(replyDeleteService, Integer.parseInt(request.getParameter("rno")));
					
					//자동으로 리스트로 이동시켜야 한다.
					jsp ="redirect:view.do?no="+request.getParameter("no")+
							"&cnt=0"
							+ "&page=" + request.getParameter("page")
							+ "&perPageNum=" + request.getParameter("perPageNum");
					break;
					
					// 게시판 댓글 쓰기 처리
				case "/board/replyWrite.do":
					// 데이터를 받아 dto를 만든 후 서비스에 전달해서 DB에 글쓰기를 한다.
					
					Execute.service(replyWriteService, 
							new BoardReplyDTO
							(Integer.parseInt(request.getParameter("no")),
							request.getParameter("content"),
							request.getParameter("writer")));
					// 자동으로 글보기로 이동시켜야 한다.
					jsp ="redirect:view.do?no="+request.getParameter("no")+
							"&cnt=0"
							+ "&page=" + request.getParameter("page")
							+ "&perPageNum=" + request.getParameter("perPageNum");
					break;
				case "/board/replyUpdate.do":
					// 데이터를 받아 dto를 만든 후 서비스에 전달해서 DB에 글쓰기를 한다.
					Execute.service(replyUpdateService, 
							new BoardReplyDTO
							(Integer.parseInt(request.getParameter("rno")),
									request.getParameter("content")));
							
					// 자동으로 리스트로 이동시켜야 한다.
					jsp ="redirect:view.do?no="+request.getParameter("no")+
							"&cnt=0"
							+ "&page=" + request.getParameter("page")
							+ "&perPageNum=" + request.getParameter("perPageNum");
					System.out.println("BoardController.execute().jsp:11"+jsp);
					break;
					
			
					
					
				default:
					break;
				}
				System.out.println("boardController.execute(). jsp"+jsp);
                
				return jsp;
	
	}

	private BoardDTO getDTO(String title, String content, String writer) {
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		return dto;
	}
	private BoardDTO getDTO(int no, String title, String content, String writer) {
		BoardDTO dto = getDTO(title, content, writer);
		dto.setNo(no);
		return dto;
	}
}
