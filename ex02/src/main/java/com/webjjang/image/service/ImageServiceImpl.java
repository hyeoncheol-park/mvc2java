package com.webjjang.image.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.webjjang.image.dto.ImageDTO;
import com.webjjang.image.mapper.ImageMapper;
import com.webjjang.util.page.PageObject;

import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j;

// 로그를 출력하기 위해서 - log.info()
//@Log4j
// 자동생성  - @Controller ,@Service,@Repository,@Compoent
//  	   @RestController, @ControllerAdvice, @ RestControllerAdvice
@Service
@Qualifier("is")
// 생성자를 이용해서 private 멤버 변수에 Di 적용 -> mapper
@AllArgsConstructor
// interface 를 상속받은 클래스라는 뜻의 Impl
public class ImageServiceImpl implements ImageService {

	// DB 처리를 위한 mapper 변수 선언
	private ImageMapper mapper;
	
	@Override
	public List<ImageDTO> list(PageObject pageObject) {
		// TODO Auto-generated method stub
		// 페이지 정보 계산 메서드 호출
		pageObject.calcuPageInfo();
		//jsp의 페이지 네이션을 위한 계산 -> jsp에 전달이 되어야 한다. request에 담는다 . (Model)
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		System.out.println("ImageController.list().pageObject:"+pageObject);
		return mapper.list(pageObject);
	}

	@Override
	public Integer write(ImageDTO dto) {
		// TODO Auto-generated method stub
		//서버에 저장되어 있는 파일의 이름을 꺼내서 fileName 프로퍼티에 넣어주는다.
//		dto.setFileNameProcess(); // controlle에서 ㅈ처리 - 중복배제 처리
		return mapper.write(dto);
	}

	@Override
	public ImageDTO view(int no) {
		// TODO Auto-generated method stub
		// 조회수 1증가 시킨다.
		mapper.increaseHit(no);
		return mapper.view(no);
	}


	@Override
	public Integer update(ImageDTO dto) {
		// TODO Auto-generated method stub
		return mapper.update(dto);
	}

	@Override
	public Integer delete(ImageDTO dto) {
		// TODO Auto-generated method stub
		return mapper.delete(dto);
	}

}
