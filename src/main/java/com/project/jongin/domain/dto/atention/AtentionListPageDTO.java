package com.project.jongin.domain.dto.atention;

import java.util.List;
import java.util.Set;

import com.project.jongin.domain.entity.AtentionEntity;
import com.project.jongin.domain.entity.BoardFilesEntity;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.HouseType;
import com.project.jongin.domain.enumes.OptionType;
import com.project.jongin.domain.enumes.PayType;

import lombok.Getter;

@Getter
public class AtentionListPageDTO {
	private long boardNo;
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
	private String memberEmail;
	private long memberNo;
	private List<BoardFilesEntity> boardFiles;
	
	public AtentionListPageDTO(AtentionEntity e) {
		boardNo=e.getBoardEntity().getBoardNo();
		boardHouseType=e.getBoardEntity().getBoardHouseType();
		boardBuildType=e.getBoardEntity().getBoardBuildType();
		boardAddress=e.getBoardEntity().getBoardAddress();
		boardAddressDetail=e.getBoardEntity().getBoardAddressDetail();
		boardPayType=e.getBoardEntity().getBoardPayType();
		boardDeposit=e.getBoardEntity().getBoardDeposit();
		boardPaymonth=e.getBoardEntity().getBoardPaymonth();
		boardSupply=e.getBoardEntity().getBoardSupply();
		boardExclusive=e.getBoardEntity().getBoardExclusive();
		boardAllFloors=e.getBoardEntity().getBoardAllFloors();
		boardPositonFloor=e.getBoardEntity().getBoardPositonFloor();
		boardInDate=e.getBoardEntity().getBoardInDate();
		boardOption=e.getBoardEntity().getBoardOption();
		boardTitle=e.getBoardEntity().getBoardTitle();
		boardContents=e.getBoardEntity().getBoardContents();
		boardHiddenMemo=e.getBoardEntity().getBoardHiddenMemo();
		memberEmail=e.getMemberEntity().getMemberEmail();
		memberNo=e.getMemberEntity().getMemberNo();
		boardFiles=e.getBoardEntity().getBoardFiles();
	}
}
