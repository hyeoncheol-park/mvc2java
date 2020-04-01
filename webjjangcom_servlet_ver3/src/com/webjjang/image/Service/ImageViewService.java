package com.webjjang.image.Service;



import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.dto.ImageDTO;
import com.webjjang.main.Service;

public class ImageViewService implements Service {
 
	private ImageDAO dao;
	public ImageViewService(ImageDAO dao) {
		this.dao=dao;
		// TODO Auto-generated constructor stub
	}
	
	public ImageDTO service(Object[] objs) throws Exception   {
		// 데이터 처리부분에 해당된다.
		//데이터 변환
		int no = (int)objs[0];
		
		System.out.println("ImageViewService.service()");
		
//		데이터를 오라클에서 가져오기 위해서 beans에서 생성한 객체를 호출한다.
		return dao.view(no);
	}
}
