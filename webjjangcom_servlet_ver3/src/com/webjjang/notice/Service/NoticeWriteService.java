package com.webjjang.notice.Service;



import com.webjjang.main.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.dto.NoticeDTO;

public class NoticeWriteService implements Service {

	private final NoticeDAO dao;
	public NoticeWriteService( NoticeDAO dao) {
		this.dao =dao;
	}
	// 데이터 Controller <-> DAO
		// 입력받은 글(NoticeDTO)를 Controller 에서 받아서 처리한다.
	public NoticeDTO service(Object[] objs) throws Exception{
			// 데이터 처리부분에 해당된다.
		
		NoticeDTO dto = (NoticeDTO) objs[0]; 
			System.out.println("NoticeWriteService.service()");
			
			// 공지사항 글쓰기에서 오류가 나고 있다는 처리를 할때 예외처리를 해준다.
			// - 예외가 발생이되면 알맞은 메시지로 바꿔서 던진다.
			try {
				// 데이터Controller에서 오라클에 저장하기 위해서 객체를 생성하고 호출한다.
				// NoticeController - NoticeWriteService - [NoticeDAO]
				
				dao.write(dto);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				// 상세한 내용의 예의메시지를 셋팅해서 다시 던지기를 할 수 있다.
				throw new Exception("글스기중 ");
			}
		}

}
