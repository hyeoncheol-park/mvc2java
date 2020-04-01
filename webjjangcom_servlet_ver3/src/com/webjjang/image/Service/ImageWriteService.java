package com.webjjang.image.Service;



import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.dto.ImageDTO;
import com.webjjang.main.Service;

public class ImageWriteService  implements Service{
	
private final ImageDAO dao;
public ImageWriteService( ImageDAO dao) {
	this.dao=dao;
}

	// 데이터 Controller <-> DAO
		// 입력받은 글(ImageDTO)를 Controller 에서 받아서 처리한다.
		public ImageDTO service(Object[] objs) throws Exception{
			// 데이터 처리부분에 해당된다.
			ImageDTO dto = (ImageDTO) objs[0];
			System.out.println("ImageWriteService.service11()");
			// 게시판 글쓰기에서 오류가 나고 있다는 처리를 할때 예외처리를 해준다.
			// - 예외가 발생이되면 알맞은 메시지로 바꿔서 던진다.
			try {
				// 데이터Controller에서 오라클에 저장하기 위해서 객체를 생성하고 호출한다.
				// ImageController - ImageWriteService - [ImageDAO]
				
				dao.write(dto);
				return null;
			} catch (Exception e) {
				// TODO: handle exception
				// 상세한 내용의 예의메시지를 셋팅해서 다시 던지기를 할 수 있다.
				throw new Exception("글스기중  오류 발생"+e);
				
			}
		}

	
		
	}
