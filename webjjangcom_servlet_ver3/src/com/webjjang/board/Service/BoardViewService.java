package com.webjjang.board.Service;



import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.dto.BoardDTO;
import com.webjjang.main.Service;

public class BoardViewService implements Service {
 
	private BoardDAO dao;
	public BoardViewService(BoardDAO dao) {
		this.dao=dao;
		// TODO Auto-generated constructor stub
	}
	
	public BoardDTO service(Object[] objs) throws Exception   {
		// 데이터 처리부분에 해당된다.
		//데이터 변환
		int no = (int)objs[0];
		int cnt = (int)objs[1];
		
		System.out.println("BoardViewService.service()");
		
//		데이터를 오라클에서 가져오기 위해서 beans에서 생성한 객체를 호출한다.
		if(cnt == 1) dao.increaseHit(no);
		return dao.view(no);
	}
}
