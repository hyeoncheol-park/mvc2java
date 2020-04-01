package com.webjjang.image.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import com.webjjang.image.dto.ImageDTO;
import com.webjjang.util.db.DBUtil;
import com.webjjang.util.page.PageObject;
import com.webjjang.util.page.SearchCondition;

public class ImageDAO {

	
	String url = "jdbc:oracle:thin:@402-oracle:1521:orcl";
	String id = "c##java09";
	String pw = "java09";
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";	
	
	
	
	public List<ImageDTO>list(PageObject pageObject) throws Exception{
		System.out.println("ImageDAO.list()");
		List<ImageDTO> list =null;
		
		
		
				Connection con = DBUtil.getConnection();
		
				String sql ="select no , title, id,"
				+ "to_char(writeDate,'yyyy-mm-dd') writeDate,"
				+ "fileName " 
				  +  " from image ";
				sql += SearchCondition.getSearchSQLWithWhere(pageObject);
				  sql += "order by no desc";
				sql ="select rownum rnum, no, title, id, writeDate,fileName"
						+" from( " +sql +") ";
				sql="select * "
						+" from( " +sql +")"
						+" where rnum between ? and ?";
		System.out.println("ImageDAO.list().sql" + sql);
		
		//4. 실행객체 가져오기 / 데이터 셋팅
		PreparedStatement pstmt = con.prepareStatement(sql);
		int idx = 1;

		idx = SearchCondition.setPreparedStatement(pstmt, pageObject, idx);
		pstmt.setInt(idx++, pageObject.getStartRow());
		pstmt.setInt(idx++, pageObject.getEndRow());
		ResultSet rs = pstmt.executeQuery();
		if(rs != null) {
			while (rs.next()) {
				if(list == null)
					list = new ArrayList<ImageDTO>();
				ImageDTO dto = new ImageDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setWriteDate(rs.getString("writeDate"));
				dto.setFileName(rs.getString("fileName"));
				//list를  위해서 작은 파일(s_)로 변환을 해준다.
				//현재 파일명은 path+ 파일명이므로 파일명 바로 앞에 s 를 추가시켜주는 처리해서
				// 다시 fuleName으로 넣는다.
				
				String fileName = dto.getFileName();
				int pos = fileName.lastIndexOf("/");
				fileName = fileName.substring(0,pos+1) + "s_"
				 + fileName.substring(pos + +1);
				dto.setFileName(fileName);
				
				// 리스트 데이터가 여러개 이므로 데이터를 담은 dto 를 list에 추가 시킨다.
				list.add(dto);
			}
		}
		//7. 닫기
		
		DBUtil.close(con, pstmt, rs);
		
		System.out.println("ImageDAO.list():" + list);
		return list;
	} //end of list()
		
	// service.ImageListService -> dao.ImageDAO
		// 1-1. 게시판 리스트 데이터 가져오기 - 전체 데이터의 갯수를 가져오는 메서드
		//  데이터 가져오기가 실패하면 출력하러 갈 수가 없다. 그런 경우에는 예외처리를 반드시 해야 하므로
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  ImageController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  ImageController -> Service -> DAO
	public int getTotalRow(PageObject pageObject) throws Exception{
		
		System.out.println("ImageDAO.getTotalRow");
		
		// 가져온 결과가 저장되어 지는 변수 -> 리턴해야 하므로 리턴타입과 같아야 한다.
		int totalRow = 0;
		
		// 데이터 가져오는 처리문
		// 1. 드라이버 확인
		Connection con = DBUtil.getConnection();
		// 3. 실행한 쿼리문작성
		// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
		String sql = " select count(*) cnt from image ";
		sql += SearchCondition.getSearchSQLWithWhere(pageObject);

		System.out.println("ImageDAO.getTotal().sql:"+sql);
		// 4. 실행객체 가져오기 / 데이터 셋팅
		PreparedStatement pstmt = con.prepareStatement(sql);
		int idx = 1;
		idx = SearchCondition.setPreparedStatement(pstmt, pageObject, idx);
		
		// pstmt.setInt(?의 위치, ?를 대체해야할 값)
		
		// 5. 실행
		ResultSet rs = pstmt.executeQuery();
		// 6. 표시 / 저장
		// rs.next() : 다음 데이터로 넘어가면서 데이터가 있으면 true,없으면 false를 리턴한다.
		if(rs != null && rs.next()) {
			totalRow = rs.getInt("cnt");
		} // end of if(rs != null && rs.next())
		
		// 7. 닫기
		DBUtil.close(con, pstmt, rs);
		System.out.println("ImageDAO.getTotalRow(0.totalRow"+totalRow);
		
		return totalRow;
	} // end of view()
		public ImageDTO view(int no) throws Exception{
			
			System.out.println("ImageDAO.view().no:" + no);
			
			// 가져온 결과가 저장되어 지는 변수 -> 리턴해야 하므로 리턴타입과 같아야 한다.
			ImageDTO dto = null;
			
			// 데이터 가져오는 처리문
			// 1. 드라이버 확인
			Class.forName(driver);
			// 2. 연결 객체
			Connection con = DriverManager.getConnection(url, id, pw);
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " select no, title, content, id, "
					+ " to_char(writeDate, 'yyyy-mm-dd') writeDate , fileName "
					+ " from image where no = ? ";
			System.out.println("ImageDAO.list().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			pstmt.setInt(1, no);
			// 5. 실행
			ResultSet rs = pstmt.executeQuery();
			// 6. 표시 / 저장
			// rs.next() : 다음 데이터로 넘어가면서 데이터가 있으면 true,없으면 false를 리턴한다.
			if(rs != null && rs.next()) {
				// 게시판 하나의 데이터를 담는 객체 생성 ->ImageDTO
				dto = new ImageDTO();
				// 데이터를 담는다. rs에서 꺼내서 dto에
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setWriteDate(rs.getString("writeDate"));
				dto.setFileName(rs.getString("fileName"));
			} // end of if(rs != null && rs.next())
			
			// 7. 닫기
			con.close();
			pstmt.close();
			rs.close();
			
			System.out.println("ImageDAO.list().dto:"+dto);
			
			return dto;
		} // end of view()
		
		public void increaseHit(int no) throws Exception{
			
			// 확인해야할 데이터 - 관련글번호, 순서번호
			System.out.println("ImageDAO.inceeashit().no:" + no);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인  / 2. 연결 객체
			Connection con =DBUtil.getConnection();
//			con.setAutoCommit(false); // 기본은 true
//			con.commit();
//			con.rollback();
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " update image set hit = hit + 1 "
					+ " where no = ? ";
			System.out.println("QnaDAO.increaseHit().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			pstmt.setInt(1, no);
			// 5. 실행
			// select -> executeQuery()
			// insert, update, delete -> executeUpdate()
			int result = pstmt.executeUpdate();
			// 6. 표시 / 저장
			if(result > 0 )
				System.out.println("조회수 1증가 성공");
			else {
				System.out.println("조회수 1증가되지 않았습니다.");
				throw new Exception("조회수 1증가처리 중 오류");
			}
			
			// 7. 닫기
			DBUtil.close(con, pstmt);
			
		} // end of increaseHit()


		
		// service.ImageWriteService -> dao.ImageDAO
		// 3. 게시판 글쓰기 데이터 가져오기 - 한개 데이터 전달 받아서 DB에 저장
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  ImageController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  ImageController -> Service -> DAO
		public void write(ImageDTO dto) throws Exception{
			
			System.out.println("ImageDAO.write().dto:" + dto);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인
			Class.forName(driver);
			// 2. 연결 객체
			Connection con = DriverManager.getConnection(url, id, pw);
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " insert into image(no, title, content, id,fileName) "
					+ " values(image_seq.nextval, ?, ?, ?,?) ";
			System.out.println("ImageDAO.write().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getFileName());
			// 5. 실행
			// select -> executeQuery()
			// insert, update, delete -> executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 / 저장
			System.out.println("글쓰기 성공");
			
			// 7. 닫기
			con.close();
			pstmt.close();
			
		} // end of write()
		
		// service.ImageUpdateService -> dao.ImageDAO
		// 4. 게시판 글수정 데이터 가져오기 - 한개 데이터 전달 받아서 DB에 수정처리
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  ImageController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  ImageController -> Service -> DAO
		public int update(ImageDTO dto) throws Exception{
			
			// 확인해야할 데이터 - 번호, 제목, 내용, 작성자
			System.out.println("ImageDAO.update().dto:" + dto);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인
			// 1. 드라이버 확인 // 2. 연결 객체
			Connection con = DBUtil.getConnection();
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " update image set title = ?, content = ? ";
			// 수정인 경우 첨부파일이 들어오지 않으면 수정하지 않는다.
			// 첨부파일이 들어온 경우 처리
			if (dto.getFileName() != null && !dto.getFileName().equals(""))
					sql += " , fileName = ? ";
			sql += " where no = ? ";
			System.out.println("ImageDAO.update().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			int idx = 1;
			pstmt.setString(idx++, dto.getTitle());
			pstmt.setString(idx++, dto.getContent());
			// 첨부파일이 있는 경우 처리
			if (dto.getFileName() != null && !dto.getFileName().equals(""))
				pstmt.setString(idx++, dto.getFileName());
			pstmt.setInt(idx++, dto.getNo());
			// 5. 실행
			// select -> executeQuery()
			// insert, update, delete -> executeUpdate()
			int result = pstmt.executeUpdate();
			// 6. 표시 / 저장
			System.out.println("글수정 성공");
			
			// 7. 닫기
			con.close();
			pstmt.close();
			
			return result;
		} // end of update()
		
		// service.ImageUpdateService -> dao.ImageDAO
		// 5. 게시판 글삭제 글번호 데이터 가져오기 - 글번호 전달 받아서 DB에 수정처리
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  ImageController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  ImageController -> Service -> DAO
		public int delete(int no) throws Exception{
			
			// 확인해야할 데이터 - 번호,
			System.out.println("ImageDAO.delete().dto:" + no);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인
			Class.forName(driver);
			// 2. 연결 객체
			Connection con = DriverManager.getConnection(url, id, pw);
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " delete from image  "
					  + " where no = ? ";
			System.out.println("ImageDAO.delete().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
		
			pstmt.setInt(1, no);
			// 5. 실행
			// select -> executeQuery()
			// insert, update, delete -> executeUpdate()
			
			int result= pstmt.executeUpdate();
			// 6. 표시 / 저장
		
			System.out.println("글삭제 성공");
			
			// 7. 닫기
			con.close();
			pstmt.close();
			 return result;
		} // end of delete()
		
			
	}

