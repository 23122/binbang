package com.project.jongin.domain.dto.board;

import java.util.List;
import java.util.Set;

import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.entity.BoardFilesEntity;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.HouseType;
import com.project.jongin.domain.enumes.OptionType;
import com.project.jongin.domain.enumes.PayType;

import lombok.Getter;
	
@Getter
public class BoardSumDTO {
	private long boardNo;
	private HouseType boardHouseType;
	private BuildType boardBuildType;
	private String boardAddress;
	private PayType boardPayType;
	private int boardDeposit;
	private int boardPaymonth;
	private int boardSupply;
	private int boardExclusive;
	private int boardAllFloors;
	private int boardPositonFloor;
	private Set<OptionType> boardOption;
	private String boardTitle;
	private long memberNo;
	private List<BoardFilesEntity> boardFiles;
	private int attentionCount;
	
	public BoardSumDTO(BoardEntity e) {
		boardNo=e.getBoardNo();
		boardHouseType=e.getBoardHouseType();
		boardBuildType=e.getBoardBuildType();
		boardAddress=e.getBoardAddress();
		boardPayType=e.getBoardPayType();
		boardDeposit=e.getBoardDeposit();
		boardPaymonth=e.getBoardPaymonth();
		boardSupply=e.getBoardSupply();
		boardExclusive=e.getBoardExclusive();
		boardAllFloors=e.getBoardAllFloors();
		boardPositonFloor=e.getBoardPositonFloor();
		boardOption=e.getBoardOption();
		boardTitle=e.getBoardTitle();
		memberNo=e.getMemberEntity().getMemberNo();
		boardFiles=e.getBoardFiles();
	}
}
