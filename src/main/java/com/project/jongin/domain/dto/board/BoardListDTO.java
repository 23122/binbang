package com.project.jongin.domain.dto.board;

import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.enumes.PayType;

import lombok.Getter;

@Getter
public class BoardListDTO {
	private long boardNo;
	private String boardTitle;
	private String boardWriter;
	private int boardPaymonth;
	private int boardDeposit;
	private PayType boardPayType;
	private String boardAddress;
	
	public BoardListDTO(BoardEntity e) {
		boardNo=e.getBoardNo();
		boardTitle=e.getBoardTitle();
		boardWriter=e.getMemberEntity().getMemberEmail();
		boardPaymonth=e.getBoardPaymonth();
		boardPayType=e.getBoardPayType();
		boardAddress=e.getBoardAddress();
		boardDeposit=e.getBoardDeposit();
	}
}
