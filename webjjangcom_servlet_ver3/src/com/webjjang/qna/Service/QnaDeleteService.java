package com.webjjang.qna.Service;



import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.qna.dto.QnaDTO;

public class QnaDeleteService {

	// 데이터 Controller <-> service <-> DAO
		// 입력받은 글번호를(QnaDTO)를 Controller 에서 받아서 삭제처리한다.
		public void service(int no) throws Exception{
			// 데이터 처리부분에 해당된다.
			System.out.println("QnaDeleteService.service()");
			
			// 질문답변 글삭제에서 오류가 나고 있다는 처리를 할때 예외처리를 해준다.
			// - 예외가 발생이되면 알맞은 메시지로 바꿔서 던진다.
			try {
				// 데이터Controller에서 오라클에 저장하기 위해서 객체를 생성하고 호출한다.
				// QnaController - QnaWriteService - [QnaDAO]
				QnaDAO dao = new QnaDAO();
				dao.delete(no);
			} catch (Exception e) {
				// TODO: handle exception
				//개발자를 위한 오류 출력
				e.printStackTrace();
				// 상세한 내용의 예의메시지를 셋팅해서 다시 던지기를 할 수 있다.
				throw new Exception("질문답변 삭제 중 오류가 발생되었습니다.");
			}
		}
		
	}