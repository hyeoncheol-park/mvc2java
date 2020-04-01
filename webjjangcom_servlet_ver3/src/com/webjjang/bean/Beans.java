package com.webjjang.bean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.util.HashMap;
import java.util.Map;

import com.undercar.faq.controller.FaqController;
import com.undercar.faq.dao.FaqDAO;
import com.undercar.faq.service.FaqDeleteService;
import com.undercar.faq.service.FaqListService;
import com.undercar.faq.service.FaqUpdateService;
import com.undercar.faq.service.FaqViewService;
import com.undercar.faq.service.FaqWriteService;
import com.webjjang.board.Service.BoardDeleteService;
import com.webjjang.board.Service.BoardListService;
import com.webjjang.board.Service.BoardReplyDeleteService;
import com.webjjang.board.Service.BoardReplyListService;
import com.webjjang.board.Service.BoardReplyUpdateService;
import com.webjjang.board.Service.BoardReplyWriteService;
import com.webjjang.board.Service.BoardUpdateService;
import com.webjjang.board.Service.BoardViewService;
import com.webjjang.board.Service.BoardWriteService;
import com.webjjang.board.controller.BoardController;
import com.webjjang.board.dao.BoardDAO;
import com.webjjang.image.Service.ImageDeleteService;
import com.webjjang.image.Service.ImageListService;
import com.webjjang.image.Service.ImageUpdateService;
import com.webjjang.image.Service.ImageViewService;
import com.webjjang.image.Service.ImageWriteService;
import com.webjjang.image.controller.ImageController;
import com.webjjang.image.dao.ImageDAO;
import com.webjjang.main.AjaxController;
import com.webjjang.main.Controller;
import com.webjjang.main.Service;
import com.webjjang.main.controller.MainController;
import com.webjjang.member.controller.MemberController;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.service.MemberIdCheckService;
import com.webjjang.member.service.MemberJoinService;
import com.webjjang.member.service.MemberLoginService;
import com.webjjang.notice.Service.NoticeDeleteService;
import com.webjjang.notice.Service.NoticeListService;
import com.webjjang.notice.Service.NoticeUpdateService;
import com.webjjang.notice.Service.NoticeViewService;
import com.webjjang.notice.Service.NoticeWriteService;
import com.webjjang.notice.controller.NoticeController;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.qna.Service.QnaListService;
import com.webjjang.qna.Service.QnaUpdateService;
import com.webjjang.qna.Service.QnaViewService;
import com.webjjang.qna.Service.QnaWriteService;
import com.webjjang.qna.dao.QnaDAO;
/**
 * Servlet implementation class Beans
 * Java에서 사용하던 Controller를 URL을 이용한 JSP난 Servlet이 담당하므로 사라진다.
 */
@WebServlet(urlPatterns = "/init",loadOnStartup =  1)
public class Beans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//각가의 객체를 저장할 map객체 선언하고 생성해 놓는다.
	private static Map<String, Controller> controllers = new HashMap<>();	
	private static Map<String, Service> services	=new HashMap<>();	
	private static Map<String,Object> daos	=new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Beans() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//클래스가 시작될때 딱한번만 실행된다. -> static 블록 호출 당한다.
		System.out.println("Beans.init() =static{} 블록");
		// 공지사항
				System.out.println("객체 초기화 중 ..");
				//*****************공지사항***
				// dao 생성 저장
				daos.put("noticeDAO", new NoticeDAO());
				// service 생성 저장
				services.put("noticeListService",
					new NoticeListService((NoticeDAO) daos.get("noticeDAO")));
				services.put("noticeViewService",
						new NoticeViewService((NoticeDAO) daos.get("noticeDAO")));
				services.put("noticeWriteService",
						new NoticeWriteService((NoticeDAO) daos.get("noticeDAO")));
				services.put("noticeUpdateService",
						new NoticeUpdateService((NoticeDAO) daos.get("noticeDAO")));
				services.put("noticeDelteService",
						new NoticeDeleteService((NoticeDAO) daos.get("noticeDAO")));
				
				controllers.put("noticeController",new NoticeController( services.get("noticeListService"),
															             services.get("noticeViewService"),
															             services.get("noticeWriteService"), 
															             services.get("noticeListService"), 
															             services.get("noticeDeleteService")));
				
				//******** 게시판  *******//
				// dao 생성 저장
				daos.put("boardDAO", new BoardDAO());
				// service 생성 저장
				services.put("boardListService",
						new BoardListService((BoardDAO) daos.get("boardDAO")));
				services.put("boardViewService",
						new BoardViewService((BoardDAO) daos.get("boardDAO")));
				services.put("boardWriteService",
						new BoardWriteService((BoardDAO) daos.get("boardDAO")));
				services.put("boardUpdateService",
						new BoardUpdateService((BoardDAO) daos.get("boardDAO")));
				services.put("boardDeleteService",
						new BoardDeleteService((BoardDAO) daos.get("boardDAO")));
				services.put("boardReplyListService",
						new BoardReplyListService((BoardDAO) daos.get("boardDAO")));
				services.put("boardReplyWriteService",
						new BoardReplyWriteService((BoardDAO) daos.get("boardDAO")));
				services.put("boardReplyUpdateService",
						new BoardReplyUpdateService((BoardDAO) daos.get("boardDAO")));
				services.put("boardReplyDeleteService",
						new BoardReplyDeleteService((BoardDAO) daos.get("boardDAO")));
//			
				
				
				controllers.put("boardController",
						 new BoardController(services.get("boardListService"),
								             services.get("boardViewService"),
								             services.get("boardWriteService"), 
								             services.get("boardUpdateService"), 
								             services.get("boardDeleteService"),
								             services.get("boardReplyListService"),
								             services.get("boardReplyWriteService"),
								             services.get("boardReplyUpdateService"),
								             services.get("boardReplyDeleteService")
								             ));
				//******** 이미지  *******//
				// dao 생성 저장
				daos.put("imageDAO", new ImageDAO());
				// service 생성 저장
				services.put("imageListService",
						new ImageListService((ImageDAO) daos.get("imageDAO")));
				services.put("imageViewService",
						new ImageViewService((ImageDAO) daos.get("imageDAO")));
				services.put("imageWriteService",
						new ImageWriteService((ImageDAO) daos.get("imageDAO")));
				services.put("imageUpdateService",
						new ImageUpdateService((ImageDAO) daos.get("imageDAO")));
				services.put("imageDeleteService",
						new ImageDeleteService((ImageDAO) daos.get("imageDAO")));
//			
				
				
				controllers.put("imageController",
						new ImageController(services.get("imageListService"),
								services.get("imageViewService"),
								services.get("imageWriteService"), 
								services.get("imageUpdateService"), 
								services.get("imageDeleteService")));
				//******** 이미지  *******//
				// dao 생성 저장
				daos.put("faqDAO", new FaqDAO());
				// service 생성 저장
				services.put("faqListService",
						new FaqListService((FaqDAO) daos.get("faqDAO")));
				services.put("faqViewService",
						new FaqViewService((FaqDAO) daos.get("faqDAO")));
				services.put("faqWriteService",
						new FaqWriteService((FaqDAO) daos.get("faqDAO")));
				services.put("faqUpdateService",
						new FaqUpdateService((FaqDAO) daos.get("faqDAO")));
				services.put("faqDeleteService",
						new FaqDeleteService((FaqDAO) daos.get("faqDAO")));
//			
				
				
				controllers.put("faqController",
						new FaqController(services.get("faqListService"),
								services.get("faqViewService"),
								services.get("faqWriteService"), 
								services.get("faqUpdateService"), 
								services.get("faqDeleteService")));
				
				//******** 회원관리  *******//
				// dao 생성 저장
				daos.put("memberDAO", new MemberDAO());
				// service 생성 저장
				services.put("memberJoinService",
						new MemberJoinService((MemberDAO) daos.get("memberDAO")));
				services.put("memberLoginService",
						new MemberLoginService((MemberDAO) daos.get("memberDAO")));
				controllers.put("memberController",
						new MemberController(services.get("memberListService"),
								services.get("memberViewService"),
								services.get("memberWriteService"), 
								services.get("memberListService"), 
								services.get("memberDeleteService")));
                
				//******** 질문답변  *******//
				// dao 생성 저장
				daos.put("qnaDAO", new QnaDAO());
				// service 생성 저장
				services.put("qnaListService",
						new QnaListService((QnaDAO) daos.get("qnaDAO")));
				services.put("qnaViewService",
						new QnaViewService((QnaDAO) daos.get("qnaDAO")));
				services.put("qnaWriteService",
						new QnaWriteService((QnaDAO) daos.get("qnaDAO")));
//				services.put("qnaAnswerService",
//						new QnaAnswerService((QnaDAO) daos.get("qnaDAO")));
				services.put("qnaUpdateService",
						new QnaUpdateService((QnaDAO) daos.get("qnaDAO")));
				
				
//					//******** Ajax 처리 등록  *******//
				// 아이디 중복 체크 dao -> memberDAO(이미 등록되어 있음)
				// 아이디 중복 체크 service
				services.put("memberIdCheckService",
						new MemberIdCheckService((MemberDAO)daos.get("memberDAO")));
				// Controller 생성 저장 -> 기본생성자 -> DI 적용은 setter를 이용해서 한다.
				AjaxController ajaxController = new AjaxController();
				controllers.put("ajaxController", ajaxController);
				// ajaxController에 대한 DI 적용을  setter를 이용해서 한다.
				ajaxController.setmemberIdCheckService(services.get("memberIdCheckService"));
				
//					//******** Main controller 처리 등록  *******//
				controllers.put("mainController",
						new MainController(services.get("noticeListService"),
								services.get("imageListService")));

				
			System.out.println("객체 초기화 완료 ..");
	}//end of init()

		//Controller 를 받아내는 메서드 작성
		public static Service getService(String name) {
			return services.get(name);
			
		}

		
		public static Controller getController(String name) {
			// TODO Auto-generated method stub
			return controllers.get(name);
		}

}//end of Beans class
