package com.project.jongin.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.OptionType;
import com.project.jongin.domain.enumes.PayType;
import com.project.jongin.domain.enumes.HouseType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "board")
public class BoardEntity extends BaseTimeEntity{
	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long boardNo;
	//종류선택
	@Enumerated(EnumType.STRING)
	private HouseType boardHouseType;
	//건물유형
	@Enumerated(EnumType.STRING)
	private BuildType boardBuildType;
	//집주소
	@Column(nullable = false)
	private String boardAddress;
	//상세주소
	@Column(nullable = false)
	private String boardAddressDetail;
	//거래종류
	@Enumerated(EnumType.STRING)
	private PayType boardPayType;
	//보증금 및 전세금
	@Column(nullable = false)
	private int boardDeposit;
	//월세
	@Column
	private int boardPaymonth;
	//공급면적
	@Column(nullable = false)
	private int boardSupply;
	//전용면적
	@Column(nullable = false)
	private int boardExclusive;
	//건물층수
	@Column(nullable = false)
	private int boardAllFloors;
	//매물층수
	@Column(nullable = false)
	private int boardPositonFloor;
	//입주가능일
	@Column(nullable = false)
	private String boardInDate;
	//옵션
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<OptionType> boardOption =new HashSet<>();
	//제목
	@Column(nullable = false)
	private String boardTitle;
	//내용
	@Column(columnDefinition = "text not null")
	private String boardContents;
	//비공개메모
	@Column
	private String boardHiddenMemo;
	
	@JoinColumn(name = "memberNo",nullable = false)
	@ManyToOne
	private MemberEntity memberEntity;
	
	@Builder.Default
	@JoinColumn(name = "boardNo")
	@OneToMany
	private List<BoardFilesEntity> boardFiles = new ArrayList<>();
	
	public BoardEntity addFile(BoardFilesEntity boardFilesEntity) {
		boardFiles.add(boardFilesEntity);
		return this;
	}
}
