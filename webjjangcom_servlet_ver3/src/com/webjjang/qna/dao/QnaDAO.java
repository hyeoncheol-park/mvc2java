package com.webjjang.qna.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.qna.dto.QnaDTO;
import com.webjjang.util.db.DBUtil;

public class QnaDAO {

	//객체에서 사용할  DB -> list(), view() ..  다른 메서드에서 다 필요로 하기 때문에
	//전역변수로 선언한다.
	
	
	
	
	//servic.QnaListServic -> dao.QnaDAO
	// 1. 질문답변 리스 데이터 가져오기
	// 데이터 가져오기가 실패하면 출력하러 갈 수가 없다. 그런경우에는 예외처리를 반드시 해야 하므로
	// 여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
	public List<QnaDTO>list() throws Exception{
		System.out.println("QnaDAO.list()");
		//가져온 결과가 저장되어 지는 변수 - > 리턴해야 하므로 리턴 타입과 같아야한다
		List<QnaDTO> list =null;
		
		//데이터 가져오는 처리문
		
		
		// 1. 드라이버 확인
		// 1. 드라이버 확인 // 2. 연결 객체
				Connection con = DBUtil.getConnection();
		
				String sql = " select q.no, q.title, q.id, m.name, "
						+ " to_char(q.writeDate, 'yyyy-mm-dd') writeDate , "
						+ " q.hit, q.levNo "
						+ " from qna q, member m "
						+ " where q.id = m.id " // join 조건
						+ " order by refNo desc, ordNo asc ";
		System.out.println("QnaDAO.list().sql" + sql);
		
		//4. 실행객체 가져오기 / 데이터 셋팅
		PreparedStatement pstmt = con.prepareStatement(sql);
		//5. 실행
		// select -> executeQuery()로 실행 ResultSet 이 리턴도되서 나옴
		// insert, update,delete -> executeUpdata() 로 실행 -> int - 1/0
		ResultSet rs = pstmt.executeQuery();
		//6. 표시 .저장
		if(rs != null) {
			//rs.next() : 다음 데이터로 넘어가면 투르 없으면펄스 리턴한다
			while (rs.next()) {
				//최종적으로 저장할 list가 null이면 생성해서 사용가능하도록 해준다.
				//ArrayLists는 일종으로 배열을 사용한다 (연속된 주소)
				if(list == null)
					list = new ArrayList<QnaDTO>();
				// 질문답변 하나의 데이터를 객세 생성 ->QnaDTO
				QnaDTO dto = new QnaDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setWriteDate(rs.getString("writeDate"));
				dto.setHit(rs.getInt("hit"));
				dto.setLevNo(rs.getInt("levNo"));
				
				// 리스트 데이터가 여러개 이므로 데이터를 담은 dto 를 list에 추가 시킨다.
				list.add(dto);
			}
		}
		//7. 닫기
		
		DBUtil.close(con, pstmt, rs);
		
		System.out.println("QnaDAO.list():" + list);
		return list;
	} //end of list()
		
	// service.QnaViewService -> dao.QnaDAO
		// 2. 질문답변 글보기 데이터 가져오기 - 한개 데이터 - 글번호에 따라서 결정(전달 받는다.)
		//  데이터 가져오기가 실패하면 출력하러 갈 수가 없다. 그런 경우에는 예외처리를 반드시 해야 하므로
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  QnaController -> Service -> DAO
		public QnaDTO view(int no) throws Exception{
			
			System.out.println("QnaDAO.view().no:" + no);
			
			// 가져온 결과가 저장되어 지는 변수 -> 리턴해야 하므로 리턴타입과 같아야 한다.
			QnaDTO dto = null;
			
			// 데이터 가져오는 처리문
			// 1. 드라이버 확인  // 2. 연결 객체
			
			Connection con = DBUtil.getConnection();
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " select q.no, q.title, q.content, q.id,m.name, "
					+ " to_char(q.writeDate, 'yyyy-mm-dd') writeDate , q.hit,"
					+ " q.refNo,q.ordNo,q.levNo,q.parentNo "
					+ " from qna q, member m where q.no = ?"//일반조건
					+ " and q.id = m.id "; //조인조건
			
			//sql 문장확인하기 위해 출력
			System.out.println("QnaDAO.list().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			pstmt.setInt(1, no);
			// 5. 실행
			ResultSet rs = pstmt.executeQuery();
			// 6. 표시 / 저장
			// rs.next() : 다음 데이터로 넘어가면서 데이터가 있으면 true,없으면 false를 리턴한다.
			if(rs != null && rs.next()) {
				// 질문답변 하나의 데이터를 담는 객체 생성 ->QnaDTO
				dto = new QnaDTO();
				// 데이터를 담는다. rs에서 꺼내서 dto에
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setWriteDate(rs.getString("writeDate"));
				dto.setHit(rs.getInt("hit"));
				dto.setRefNo(rs.getInt("refNo"));
				dto.setOrdNo(rs.getInt("ordNo"));
				dto.setLevNo(rs.getInt("levNo"));
				dto.setParentNo(rs.getInt("parentNo"));
			} // end of if(rs != null && rs.next())
			
			// 7. 닫기
			con.close();
			pstmt.close();
			rs.close();
			
			System.out.println("QnaDAO.list().dto:"+dto);
			
			return dto;
		} // end of view()
		// service.QnaviewService -> dao.QnaDAO
				// 4-1.QnaController.main() 2. 글보기를 한경우 조회수르 1증가시킨다.
				//데이터 가져오기 - 한개 데이터 전달 받아서 DB에 수정처리
				//답변 데이터를 넣기 전에 실행해야만 합니다.
						//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
				//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
				//  QnaController -> Service -> DAO
				public void increaseHit(int no) throws Exception{
					
					// 확인해야할 데이터 - 관련글번호,순서번호 
					System.out.println("QnaDAO.update().no:" + no);
					
					// 데이터 저장 처리문
					// 1. 드라이버 확인
					//Class.forName(driver);
					// 2. 연결 객체
			
					Connection  con = DBUtil.getConnection();
					//con.setAutoCommit(false); // 기본은 true
					//con.commit();
					//con.rollback();
					// 3. 실행한 쿼리문작성
					// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
					String sql = " update qna set hit = hit +1 "					
							+ " where no  = ?  ";
					System.out.println("QnaDAO.update().sql:"+sql);
					// 4. 실행객체 가져오기 / 데이터 셋팅
					PreparedStatement pstmt = con.prepareStatement(sql);
					// pstmt.setInt(?의 위치, ?를 대체해야할 값)
					pstmt.setInt(1,no);
					
				
					// 5. 실행
					// select -> executeQuery()
					// insert, update, delete -> executeUpdate()
					int result = pstmt.executeUpdate();
					// 6. 표시 / 저장
					if(result > 0 )
					System.out.println("조회수 1증가 성공");
					//순서번호가 3번까지만 있다. 4번을 답변하고자 한다. -> 같거나 큰 데이터가 없으므로 수정되지 않는다.
					else {
						System.out.println("조회수 1증가되지 않았습니다. ");
						throw new Exception("조회수 1증가처리중 오류");
					}// 7. 닫기
					//con.close();
					//pstmt.close();
					DBUtil.close(con, pstmt);
				} // end of increaseHit()
		
		// service.QnaWriteService -> dao.QnaDAO
		// 3. 질문답변 글쓰기 데이터 가져오기 - 한개 데이터 전달 받아서 DB에 저장
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  QnaController -> Service -> DAO
		public int write(QnaDTO dto) throws Exception{
			// 제목질문 ,내용,아이디 -> 나머지 기본값이라 확인 불필요
			System.out.println("QnaDAO.write().dto:" + dto);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인			
			// 2. 연결 객체
			Connection con = DBUtil.getConnection();
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " insert into qna(no, title, content,id,refNo,ordNo,levNo) "
					+ " values(qna_seq.nextval, ?, ?,?,qna_seq.nextval,1,0) ";
			System.out.println("QnaDAO.write().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getId());
	
			// 5. 실행
			// select -> executeQuery()
			// insert, update, delete -> executeUpdate()
			int result = pstmt.executeUpdate();
			// 6. 표시 / 저장
			System.out.println("질문하기 성공");
			
			// 7. 닫기
			DBUtil.close(con, pstmt);
			return result;
			
		} // end of write()
		// service.QnaWriteService -> dao.QnaDAO
				// 4. 답변 글쓰기 데이터 가져오기 - 한개 데이터 전달 받아서 DB에 저장
				//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
				//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
				//  QnaController -> Service -> DAO
				public void answer(QnaDTO dto) throws Exception{
					// 제목질문 ,내용,아이디,관련번호 ,순서번호,들여쓰기 ,부모글번호 -> 나머지 기본값이라 확인 불필요
					System.out.println("QnaDAO.answer().dto:" + dto);
					
					// 데이터 저장 처리문
					// 1. 드라이버 확인			
					// 2. 연결 객체
					Connection con = DBUtil.getConnection();
					// 3. 실행한 쿼리문작성
					// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
					String sql = " insert into qna(no, title, content,id,refNo,ordNo,levNo,parentNo) "
							+ " values(qna_seq.nextval,?,?,?,?,?,?,?) ";
					System.out.println("QnaDAO.write().sql:"+sql);
					// 4. 실행객체 가져오기 / 데이터 셋팅
					PreparedStatement pstmt = con.prepareStatement(sql);
					// pstmt.setInt(?의 위치, ?를 대체해야할 값)
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setString(3, dto.getId());
					pstmt.setInt(4, dto.getRefNo());
					pstmt.setInt(5, dto.getOrdNo());
					pstmt.setInt(6, dto.getLevNo());
					pstmt.setInt(7, dto.getParentNo());
			
					// 5. 실행
					// select -> executeQuery()
					// insert, update, delete -> executeUpdate()
					pstmt.executeUpdate();
					// 6. 표시 / 저장
					System.out.println("답변하기 성공");
					
					// 7. 닫기
					DBUtil.close(con, pstmt);
					
				} // end of write()
		// service.QnaUpdateService -> dao.QnaDAO
		// 4-1. 답변을 하려면 가져온 순서번호 보다 같거나 큰 순서번호를 1증가시킨다
		//데이터 가져오기 - 한개 데이터 전달 받아서 DB에 수정처리
		//답변 데이터를 넣기 전에 실행해야만 합니다.
				//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  QnaController -> Service -> DAO
		public void increaseOrdNo(QnaDTO dto) throws Exception{
			
			// 확인해야할 데이터 - 관련글번호,순서번호 
			System.out.println("QnaDAO.update().dto:" + dto);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인
			//Class.forName(driver);
			// 2. 연결 객체
	
			Connection  con = DBUtil.getConnection();
			//con.setAutoCommit(false); // 기본은 true
			//con.commit();
			//con.rollback();
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " update qna set ordNo = ordNo +1 "					
					+ " where refNo  = ? and ordNo >= ? ";
			System.out.println("QnaDAO.update().sql:"+sql);
			// 4. 실행객체 가져오기 / 데이터 셋팅
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setInt(?의 위치, ?를 대체해야할 값)
			pstmt.setInt(1, dto.getRefNo());
			pstmt.setInt(2, dto.getOrdNo());
		
			// 5. 실행
			// select -> executeQuery()
			// insert, update, delete -> executeUpdate()
			int result = pstmt.executeUpdate();
			// 6. 표시 / 저장
			if(result > 0 )
			System.out.println("순서번호 1증가 성공");
			//순서번호가 3번까지만 있다. 4번을 답변하고자 한다. -> 같거나 큰 데이터가 없으므로 수정되지 않는다.
			else System.out.println("순서번호 1증가 않았습니다. ");
			// 7. 닫기
			//con.close();
			//pstmt.close();
			DBUtil.close(con, pstmt);
		} // end of increaseOrdNo
		 
			// 4. 질문답변 글수정 데이터 가져오기 - 한개 데이터 전달 받아서 DB에 수정처리
			//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
			//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
			//  QnaController -> Service -> DAO
			public int update(QnaDTO dto) throws Exception{
				
				// 확인해야할 데이터 - 번호, 제목, 내용, 
				System.out.println("QnaDAO.update().dto:" + dto);
				
				// 데이터 저장 처리문
				// 1. 드라이버 확인
				//Class.forName(driver);
				// 2. 연결 객체
			//	Connection con = DriverManager.getConnection(url, id, pw);
				Connection  con = DBUtil.getConnection();
				// 3. 실행한 쿼리문작성
				// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
				String sql = " update qna set title = ?, content = ? "					
						+ " where no = ? ";
				System.out.println("QnaDAO.update().sql:"+sql);
				// 4. 실행객체 가져오기 / 데이터 셋팅
				PreparedStatement pstmt = con.prepareStatement(sql);
				// pstmt.setInt(?의 위치, ?를 대체해야할 값)
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getNo());
				// 5. 실행
				// select -> executeQuery()
				// insert, update, delete -> executeUpdate()
				int result = pstmt.executeUpdate();
				// 6. 표시 / 저장
				if(result > 0 )
				System.out.println("글수정 성공");
				else System.out.println("글수정이 되지 않았습니다. -글번호를 확인하세요");
				// 7. 닫기
				//con.close();
				//pstmt.close();
				DBUtil.close(con, pstmt);
				return result;
			} // end of update()	
		// service.QnaUpdateService -> dao.QnaDAO
		// 5. 질문답변 글삭제 글번호 데이터 가져오기 - 글번호 전달 받아서 DB에 수정처리
		//  여기서는 예외처리하지 않고 throw 시키는 것으로 할 수 있다.
		//  QnaController : 실행내용 결정 - 데이터 수집 / 데이터 표시
		//  QnaController -> Service -> DAO
		public void delete(int no) throws Exception{
			
			// 확인해야할 데이터 - 번호,
			System.out.println("QnaDAO.delete().dto:" + no);
			
			// 데이터 저장 처리문
			// 1. 드라이버 확인
			
			// 2. 연결 객체
		Connection con = DBUtil.getConnection();
			// 3. 실행한 쿼리문작성
			// 쿼리 문 중에서 ?는 값을 대체 시키는 대체 문자에 해당이된다.
			String sql = " delete from qna  "
					  + " where no = ? ";
			System.out.println("QnaDAO.delete().sql:"+sql);
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
			System.out.println("글삭제 성공");
			else
				System.out.println("글삭제가 되지 않았습니다. -글번호를 확인해주세요");
			// 7. 닫기
			DBUtil.close(con, pstmt);
			
		} // end of delete()
		
			
	}

