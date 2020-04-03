package com.webjjang.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webjjang.member.dto.LoginDTO;
import com.webjjang.member.dto.MemberDTO;
import com.webjjang.member.service.MemberService;
import com.webjjang.util.page.PageObject;

//import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member")
// 생성자에 의해서 멤버 변수를 초기화 시키는 작업을 한다.
// 멤버 변수 중에서 직접 지정한 변수들을 따로 초기화 작업을 한 경우 _> @Autowirde나 @Inject 사용
//@AllArgsConstructor
public class MemberController {

	@Autowired
	@Qualifier("ms")
	private MemberService service;
	
	private final String module ="member";
	
	//1. 회원 리스트
	@GetMapping("/list.do")
	public String list(Model model,PageObject pageObject) {
//		System.out.println("MemberController.list().pageObjecet"+pageObject);
		// DB에서 데이터 가져오기 -> 페이지 계산, JSP에서 표시할 페이지네이션 정보가 계산된다.
		model.addAttribute("list",service.list(pageObject));
		model.addAttribute("pageObjecet",pageObject);
		//WEB-INF/views/+member+/list+.jsp
		return module + "/list";
	}
	
	//2. 회원 글보기
	@GetMapping("/view.do")
	public String view(Model model,int no) {
		model.addAttribute("dto",service.view(no));
		//WEB-INF/views/+member+/view+.jsp
		return module + "/view";
	}
	
	//3. 회원 글 쓰기 폼 
	@GetMapping("/write.do")
	public String writeForm() {
		//WEB-INF/views/+member+/write+.jsp
		return module + "/write";
	}
	//3-1. 회원 글가입 처리
	@PostMapping("/write.do")
	public String write(MemberDTO dto) {
		service.write(dto);
		// 자동으로 리스트로 간다.
		return "redirect:list.do";
	}
	
	//4. 회원 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(Model model,int no) {
		model.addAttribute("dto",service.view(no));
		//WEB-INF/views/+member+/update+.jsp
		return module + "/update";
	}
	
	//4-1. 회원 글수정 처리 -전체 데이터
	@PostMapping("/update.do")
	public String update(MemberDTO dto) {
		int result=service.update(dto);
		if(result >0)
		// 자동으로 글보기 이동한다
			return "redirect:view.do?no=" + dto.getNo();
		else
			//오류 보여주는 jsp페이지로 이동 -> 수정이 정상적으로 안된 경우 : 비밀번호가 틀림
			return "error/error_pw";
	}
	
	//5. 회원 탈퇴 처리 - 아이디 , 비밀번호 , 이메일
	@PostMapping("/delete.do") // 비밀번호가 잇으므로 보이지 않게 post 방식으로 전달
	public String delete(MemberDTO dto) {
		//WEB-INF/views/+member+/list+.jsp
		service.delete(dto);
		// 자동으로 리스트로 이동
		return "redirect:list.do";
	}
	// 6. 로그인  폼- 아이디와 비밀번호를 받는다. - post
		@GetMapping("/login.do")
		public String loginForm() {
			return "member/login";
		}
		// 6. 로그인  처리- 아이디와 비밀번호를 받는다. - post
		@PostMapping("/login.do")
		public String login(LoginDTO dto,HttpSession session) {
			// 정보가 맞으면 회원 정보가 셋팅 , 맞지 않으면 null 이 셋팅
			session.setAttribute("login", service.login(dto));
			String uri = (String) session.getAttribute("uri");
			session.removeAttribute("uri");
			return (uri ==null)? "redirect:/main/main.do":
				"redirect:"+uri;
		}
		// 6. 로그아웃 처리
		@PostMapping("/logout.do")
		public String logout(HttpSession session) {
			// 정보가 맞으면 회원 정보가 셋팅 , 맞지 않으면 null 이 셋팅
			session.invalidate(); // 세션을 지우자
			return "redirect:/main/main.do";
		}

}
