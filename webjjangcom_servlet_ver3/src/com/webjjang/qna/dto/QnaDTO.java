package com.webjjang.qna.dto;

public class QnaDTO {
	private int no;
	private String title, content, id,name, writeDate;
	private int hit, refNo, ordNo, levNo, parentNo;
	
	
	//질문답변에서 사용되는  번호들을 셋팅 -> 답변할 때만 사용
	public void setOperateNo(QnaDTO dto) {// 보고있는 글을 전달 받는다
		// 관련글번호는 그대로 넣는다.
		refNo = dto.getRefNo();
		// 순서 번호는 1 증가 시킨다.
		ordNo =dto.getOrdNo() +1;
		// 들여쓰기 1증가 시킨다.
		levNo = dto.getLevNo() +1;
		// 부모글은 원본글의 글번호를 넣는다.
		parentNo = dto.getNo();
		
		
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRefNo() {
		return refNo;
	}
	public void setRefNo(int refNo) {
		this.refNo = refNo;
	}
	public int getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}
	public int getLevNo() {
		return levNo;
	}
	public void setLevNo(int levNo) {
		this.levNo = levNo;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	
	//데이터 확인용 메서드
	@Override
	public String toString() {
		return "QnaDTO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", name=" + name
				+ ", writeDate=" + writeDate + ", hit=" + hit + ", refNo=" + refNo + ", ordNo=" + ordNo + ", levNo="
				+ levNo + ", parentNo=" + parentNo + "]";
	}
	
	

}
