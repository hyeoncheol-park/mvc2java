package com.webjjang.reply.mapper;

import java.util.List;
import java.util.Map;

import com.webjjang.reply.dto.ReplyDTO;
import com.webjjang.util.page.PageObject;

public interface ReplyMapper {
	
	//1.댓글 리스트
	public List<ReplyDTO> list(Map<String, Object> map);
	public Integer getTotalRow();
	// 2. 댓글 등록
	public Integer write(ReplyDTO dto);
	//3. 댓글 수정
	public Integer update(ReplyDTO dto);
	//4. 댓글 삭제
	public Integer delete(ReplyDTO dto);
}
