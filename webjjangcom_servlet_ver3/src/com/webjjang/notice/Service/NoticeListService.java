package com.webjjang.notice.Service;


import java.util.List;

import com.webjjang.main.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.dto.NoticeDTO;
import com.webjjang.util.page.PageObject;

public class NoticeListService implements Service{
	
	private final NoticeDAO dao;
	//사용해야할 dao  객체를 초기화 -> 밖에서 생성할 때 넣어준다.
	//Beans에서 한번만 생성해서 생성된 dao를 넣어준다.
	//NoticeListServices는 NoticaDAO를 의존(Dependency)한다.
	//실행되는 것으로 dao를 변수에 넣어준다  -> 주입(Injection)
	//위에 2개의 용어 합쳐서 의존성 주입(DI : Dependency Injection)
	public NoticeListService( NoticeDAO dao) {
		this.dao =dao;
	
	}

	@Override
	public List<NoticeDTO> service(Object[] objs)  throws Exception{
		//Objs는 파라메터로 받고  데이터 변환
		// 데이터 처리부분에 해당된다.
		PageObject pageObject =  (PageObject) objs[0];
		System.out.println("NoticeListService.service()");
		
		// 데이터를 오라클에서 가져오기 위해서 선언된 dao 객체의 메서드 호출한다.
		// NoticeController - NoticeListService - [NoticeDAO]
		//미리 생성을 하고 여러사림이 같이 쓰더라도 한번만 생성디 될수 있도록 프로그램을 한다,.
		//총 데이터 갯수를 가져오는 프로그럄 호출
		//오라클에소 테이블의 데이터 갯수를 가져와서 넣어주면서 , totalPage,startPage,endPage 
		//자동으로 계산해 준다.
		pageObject.setTotalRow(dao.getTotalRow());
		System.out.println("noticeListSerive.service().pageObject:"+pageObject);
		return dao.list(pageObject);
	}
	
}
