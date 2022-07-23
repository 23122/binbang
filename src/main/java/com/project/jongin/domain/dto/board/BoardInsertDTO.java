package com.project.jongin.domain.dto.board;

import java.util.Set;

import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.entity.MemberEntity;
import com.project.jongin.domain.enumes.HouseType;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.OptionType;
import com.project.jongin.domain.enumes.PayType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInsertDTO {
	private long boardNo;
	private long memberNo;
	private HouseType boardHouseType;
	private BuildType boardBuildType;
	private String boardAddress;
	private String boardAddressDetail;
	private PayType boardPayType;
	private int boardDeposit;
	private int boardPaymonth;
	private int boardSupply;
	private int boardExclusive;
	private int boardAllFloors;
	private int boardPositonFloor;
	private String boardInDate;
	private Set<OptionType> boardOption;
	private String boardTitle;
	private String boardContents;
	private String boardHiddenMemo;
	private String boardFilesChangeName;
	
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.boardNo(boardNo)
				.memberEntity(MemberEntity.builder().memberNo(memberNo).build())
				.boardHouseType(boardHouseType)
				.boardBuildType(boardBuildType)
				.boardAddress(boardAddress)
				.boardAddressDetail(boardAddressDetail)
				.boardPayType(boardPayType)
				.boardDeposit(boardDeposit)
				.boardPaymonth(boardPaymonth)
				.boardSupply(boardSupply)
				.boardExclusive(boardExclusive)
				.boardAllFloors(boardAllFloors)
				.boardPositonFloor(boardPositonFloor)
				.boardInDate(boardInDate)
				.boardOption(boardOption)
				.boardTitle(boardTitle)
				.boardContents(boardContents)
				.boardHiddenMemo(boardHiddenMemo)
				.build();
	}
}
