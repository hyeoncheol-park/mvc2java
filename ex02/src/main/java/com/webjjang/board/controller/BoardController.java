package com.webjjang.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParser;
import com.webjjang.board.dto.BoardDTO;
import com.webjjang.board.service.BoardService;
import com.webjjang.util.page.PageObject;

//import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@Controller
@Log4j
@RequestMapping("/board")
// 생성자에 의해서 멤버 변수를 초기화 시키는 작업을 한다.
// 멤버 변수 중에서 직접 지정한 변수들을 따로 초기화 작업을 한 경우 _> @Autowirde나 @Inject 사용
//@AllArgsConstructor
public class BoardController {

	@Autowired
	@Qualifier("bs")
	private BoardService service;
	
	private final String module ="board";
	
	//1. 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model,PageObject pageObject) {
//		System.out.println("BoardController.list().pageObjecet"+pageObject);
		// DB에서 데이터 가져오기 -> 페이지 계산, JSP에서 표시할 페이지네이션 정보가 계산된다.
		model.addAttribute("list",service.list(pageObject));
		model.addAttribute("pageObjecet",pageObject);
		//WEB-INF/views/+board+/list+.jsp
		return module + "/list";
	}
	
	//2. 게시판 글보기
	@GetMapping("/view.do")
	public String view(Model model,int no) {
		model.addAttribute("dto",service.view(no));
		//WEB-INF/views/+board+/view+.jsp
		return module + "/view";
	}
	
	//3. 게시판 글 쓰기 폼 
	@GetMapping("/write.do")
	public String writeForm() {
		//WEB-INF/views/+board+/write+.jsp
		return module + "/write";
	}
	//3-1. 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(BoardDTO dto) {
		service.write(dto);
		// 자동으로 리스트로 간다.
		return "redirect:list.do";
	}
	
	//4. 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(Model model,int no) {
		model.addAttribute("dto",service.view(no));
		//WEB-INF/views/+board+/update+.jsp
		return module + "/update";
	}
	
	//4-1. 게시판 글수정 처리 -전체 데이터
	@PostMapping("/update.do")
	public String update(BoardDTO dto) {
		int result=service.update(dto);
		if(result >0)
		// 자동으로 글보기 이동한다
			return "redirect:view.do?no=" + dto.getNo();
		else
			//오류 보여주는 jsp페이지로 이동 -> 수정이 정상적으로 안된 경우 : 비밀번호가 틀림
			return "error/error_pw";
	}
	
	//5. 게시판 글 삭제 - 글번호 , 비밀번호
	@PostMapping("/delete.do") // 비밀번호가 잇으므로 보이지 않게 post 방식으로 전달
	public String delete(BoardDTO dto) {
		//WEB-INF/views/+board+/list+.jsp
		service.delete(dto);
		// 자동으로 리스트로 이동
		return "redirect:list.do";
	}

@GetMapping("/test.do")
public String test(Model model) {
	
	String clientId = "H7SI5PudiEMFJ_Dn5vzZ"; //애플리케이션 클라이언트 아이디값"
    String clientSecret = "v705i4gGdX"; //애플리케이션 클라이언트 시크릿값"

    String text = null;
    try {
        text = URLEncoder.encode("냉면", "UTF-8");
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException("검색어 인코딩 실패",e);
    }
    System.out.println(text);
    String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + text;    // json 결과
    //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("X-Naver-Client-Id", clientId);
    requestHeaders.put("X-Naver-Client-Secret", clientSecret);
    String responseBody = get(apiURL,requestHeaders);

    System.out.println(responseBody);

    JSONParser jsonParser= new JSONParser();
    
    try {
		JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
		JSONArray items = (JSONArray) jsonObject.get("items"); 
		System.out.println("아이템스");
		
			JSONObject tempObj = (JSONObject) items.get(0);
			String a1=(String) tempObj.get("title");
			String a2=(String) tempObj.get("lprice");
			String a3=(String) tempObj.get("link");
			String a4=(String) tempObj.get("image");
			
			items.get(1);
			String b1=(String) tempObj.get("title");
			String b2=(String) tempObj.get("lprice");
			String b3=(String) tempObj.get("link");
			String b4=(String) tempObj.get("image");
			items.get(2);
			String c1=(String) tempObj.get("title");
			String c2=(String) tempObj.get("lprice");
			String c3=(String) tempObj.get("link");
			String c4=(String) tempObj.get("image");
			items.get(3);
			String d1=(String) tempObj.get("title");
			String d2=(String) tempObj.get("lprice");
			String d3=(String) tempObj.get("link");
			String d4=(String) tempObj.get("image");
			items.get(4);
			String e1=(String) tempObj.get("title");
			String e2=(String) tempObj.get("lprice");
			String e3=(String) tempObj.get("link");
			String e4=(String) tempObj.get("image");
			
			model.addAttribute("a1",a1);
			model.addAttribute("a2",a2);
			model.addAttribute("a3",a3);
			model.addAttribute("a4",a4);
			System.out.println(a1);
    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return module + "/test";
}
public void shpping(String text1) {
	
	
}

private static String get(String apiUrl, Map<String, String> requestHeaders){
    HttpURLConnection con = connect(apiUrl);
    try {
        con.setRequestMethod("GET");
        for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            return readBody(con.getInputStream());
        } else { // 에러 발생
            return readBody(con.getErrorStream());
        }
    } catch (IOException e) {
        throw new RuntimeException("API 요청과 응답 실패", e);
    } finally {
        con.disconnect();
    }
}

private static HttpURLConnection connect(String apiUrl){
    try {
        URL url = new URL(apiUrl);
        return (HttpURLConnection)url.openConnection();
    } catch (MalformedURLException e) {
        throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
    } catch (IOException e) {
        throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
    }
}

private static String readBody(InputStream body){
    InputStreamReader streamReader = new InputStreamReader(body);

    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
        StringBuilder responseBody = new StringBuilder();

        String line;
        while ((line = lineReader.readLine()) != null) {
            responseBody.append(line);
        }

        return responseBody.toString();
    } catch (IOException e) {
        throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
    }
}




}
