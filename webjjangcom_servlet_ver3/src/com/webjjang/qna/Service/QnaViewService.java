package com.webjjang.qna.Service;



import com.webjjang.main.Service;
import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.qna.dto.QnaDTO;

public class QnaViewService implements Service {

	private QnaDAO dao;
     public QnaViewService(QnaDAO dao) {
	 this.dao=dao;
	// TODO Auto-generated constructor stub
}
	@Override
	// 데이터 Controller <-> DAO
	// 글번호를 Controller 에서 받아서 처리한다.
	public QnaDTO service(Object[] objs) throws Exception{
		// 데이터 변환
		int no = (int)objs[0];
		int inc = (int)objs[1];
		
		System.out.println("QnaViewService.service()");
		
		// 데이터를 오라클에서 가져오기 위해서 객체를 생성하고 호출한다.
		// QnaController - QnaViewService - [QnaDAO]
		
		//inc의 값이 1인경우조회수 1증가 시키는 처리
		if(inc == 1)
			dao.increaseHit(no);
		return dao.view(no);
	}
	
}
