package com.webjjang.image.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.webjjang.image.dto.ImageDTO;
import com.webjjang.image.service.ImageService;
import com.webjjang.image.service.ImageService;
import com.webjjang.util.file.FileUtil;
//import com.webjjang.image.service.ImageServiceImpl;
import com.webjjang.util.page.PageObject;

//import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/image")
// 생성자에 의해서 멤버 변수를 초기화 시키는 작업을 한다.
// 멤버 변수 중에서 직접 지정한 변수들을 따로 초기화 작업을 한 경우 _> @Autowirde나 @Inject 사용
//@AllArgsConstructor
public class ImageController {

	@Autowired
	@Qualifier("is")
	private ImageService service;
	
	private final String module ="image";
	
	
	//1. 이미지 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model,PageObject pageObject) {
//		System.out.println("ImageController.list().pageObjecet"+pageObject);
		// 이미지 게시판인 경우 이미지의 한줄에 나타낼수 있는 이미지 갯수 * n -> 갯수
		// 한주에 4개 표시 2줄 표시
		pageObject.setPerPageNum(8);
		// DB에서 데이터 가져오기 -> 페이지 계산, JSP에서 표시할 페이지네이션 정보가 계산된다.
		model.addAttribute("list",service.list(pageObject));
		model.addAttribute("pageObjecet",pageObject);
		//WEB-INF/views/+image+/list+.jsp
		return module + "/list";
	}
	//2. 이미지 게시판 글보기
	@GetMapping("/view.do")
	public String view(Model model,int no) {
		model.addAttribute("dto",service.view(no));
		//WEB-INF/views/+image+/view+.jsp
		return module + "/view";
	}
	//3. 이미지 게시판 글 쓰기 폼 
	@GetMapping("/write.do")
	public String writeForm() {
		//WEB-INF/views/+image+/write+.jsp
		return module + "/write";
	}
	//3-1. 이미지 게시판 글쓰기 처리
	// 작성자 ID는 세션으에서부터 받아와야한다.
	// 이미지 파일을 dto의  프로퍼티로 전달이 되지 않는다.( 현재 시스템)-> 메서드의 파라메테로 지정
	// -> 다시 dto 에 넣어준다.
	@PostMapping("/write.do")
	public String write(ImageDTO dto,MultipartFile multiFile,
			HttpSession session, HttpServletRequest request)
	throws Exception{
		// DTO 데이터를 자동으로 넘겨 받는다 그런데 작성자 아이디를 세션에서 부터 가져온다.
		// 로그인 처리가 되어 있지 않으므로 강제 로그인 시킨다. -member 테이블 에서 하나 선택
		dto.setId("test"); 
		dto.setMultiFile(multiFile);
		String realPath
		= request.getServletContext().getRealPath("/upload/image");
		// 넘어온 파일의 중복 배제 처리
		File saveFile = FileUtil.removeDuplicateFileName(realPath, multiFile.getOriginalFilename());
		
		System.out.println("+++++++++imageController.write().saveFile.getName"+saveFile.getName());
		//db에 저장할 파일이름 셋팅
		dto.setFileName("/upload/image/"+saveFile.getName());
	  //전달돤 파일 저장
		multiFile.transferTo(saveFile);
		System.out.println("Imagecontrillet.writ"+dto);
		service.write(dto);
		// 자동으로 리스트로 간다.
		return "redirect:list.do";
	}
	//4. 이미지 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(Model model,int no) {
		model.addAttribute("dto",service.view(no));
		//WEB-INF/views/+image+/update+.jsp
		return module + "/update";
	}
	//4-1. 이미지 게시판 글수정 처리 -전체 데이터
	@PostMapping("/update.do")
	public String update(ImageDTO dto) {
		int result=service.update(dto);
		if(result >0)
		// 자동으로 글보기 이동한다
			return "redirect:view.do?no=" + dto.getNo();
		else
			//오류 보여주는 jsp페이지로 이동 -> 수정이 정상적으로 안된 경우 : 비밀번호가 틀림
			return "error/error_pw";
	}
	//5. 이미지 게시판 글 삭제 - 글번호 , 비밀번호
	@PostMapping("/delete.do") // 비밀번호가 잇으므로 보이지 않게 post 방식으로 전달
	public String delete(ImageDTO dto) {
		//WEB-INF/views/+image+/list+.jsp
		service.delete(dto);
		// 자동으로 리스트로 이동
		return "redirect:list.do";
	}
}
