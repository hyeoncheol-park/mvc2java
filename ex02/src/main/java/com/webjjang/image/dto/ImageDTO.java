package com.webjjang.image.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
// getter,setter , 생성자,toString() 자동생성
public class ImageDTO {

	private int no;
	private String title,content,id,name;
	private Date writeDate;
	private String fileName;//server 에 올라간 path+filename
	private MultipartFile multiFile; //form 에서 넘어오는 데이토 받기
	
	// 이미지 등록 또는 수정시 필요한 처리 -> DB 저장용 파일이름을 알아 내야한다.
	public void setFileNameProcess() {
		fileName= "/upload/image/" + multiFile.getOriginalFilename();
	}
}
