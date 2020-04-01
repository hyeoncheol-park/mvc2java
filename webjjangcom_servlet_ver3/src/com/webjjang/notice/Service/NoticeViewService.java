package com.webjjang.notice.Service;



import com.webjjang.main.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.dto.NoticeDTO;

public class NoticeViewService implements Service {

	private NoticeDAO dao;
	public NoticeViewService( NoticeDAO dao) {
		this.dao =dao;
	
	}	
	@Override
	// 데이터 Controller <-> DAO
	// 글번호를 Controller 에서 받아서 처리한다.
	public NoticeDTO service(Object[] objs) throws Exception   {
		// 데이터 처리부분에 해당된다.
		int no = (int)objs[0];
		
		
		System.out.println("NoticeViewService.service()");
		
		
			return dao.view(no);
		}
	}

