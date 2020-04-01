package com.webjjang.notice.Service;



import com.webjjang.main.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.dto.NoticeDTO;

public class NoticeDeleteService implements Service {

	
	private final NoticeDAO dao;
	public NoticeDeleteService( NoticeDAO dao) {
		this.dao =dao;
	}
	
		public NoticeDTO service(Object[] objs) throws Exception{
			// 데이터 처리부분에 해당된다.
			System.out.println("NoticeDeleteService.service()");
			
			int no = (int) objs[0]; 
			dao.delete(no);
			return null;
		}
}