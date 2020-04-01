package com.webjjang.image.Service;



import com.webjjang.image.dao.ImageDAO;
import com.webjjang.main.Service;

public class ImageDeleteService implements Service {

	private ImageDAO dao;
	public ImageDeleteService(ImageDAO dao) {
		this.dao=dao;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	// 데이터 Controller <-> service <-> DAO
		// 입력받은 글번호를(ImageDTO)를 Controller 에서 받아서 삭제처리한다.
		public Integer service(Object[] objs) throws Exception{
			//데이터 변화
		int no = (int)objs[0];
		
		
		// 데이터 처리부분에 해당된다.
			System.out.println("ImageDeleteService.service()");
			
			// 게시판 글삭제에서 오류가 나고 있다는 처리를 할때 예외처리를 해준다.
		
				
				return dao.delete(no);
			
			
				
			}
		}
		
	