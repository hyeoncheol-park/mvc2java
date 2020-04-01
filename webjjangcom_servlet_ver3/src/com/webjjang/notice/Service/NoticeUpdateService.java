package com.webjjang.notice.Service;



import com.webjjang.main.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.dto.NoticeDTO;

public class NoticeUpdateService  implements Service  {

	private final NoticeDAO dao;
	public NoticeUpdateService( NoticeDAO dao) {
		this.dao =dao;
	}

	// 데이터 Controller <-> DAO
	// 입력받은 글(NoticeDTO)를 Controller 에서 받아서 처리한다.

	public NoticeDTO service(Object[] objs) throws Exception{
		NoticeDTO dto = (NoticeDTO) objs[0]; 
		// 데이터 처리부분에 해당된다.
		System.out.println("NoticeUpadteService.service()");
		
	
			
			dao.update(dto);
			return dto;
		
		}
	}




