package com.project.jongin.domain.entity;

import java.time.DateTimeException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.PayType;
import com.project.jongin.domain.enumes.BinbangType;
import com.project.jongin.domain.enumes.BoilType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sales")
public class SalesEntity extends BaseTimeEntity{
	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long salesNo;
	//종류선택
	@Enumerated(EnumType.STRING)
	private BinbangType salesType;
	//건물유형
	@Enumerated(EnumType.STRING)
	private BuildType salesBuildType;
	//집주소
	@Column(nullable = false)
	private String salesAddress;
	//거래종류
	@Enumerated(EnumType.STRING)
	private PayType salesPayType;
	//보증금 및 전세금
	@Column(nullable = false)
	private int salesDeposit;
	//월세
	@Column(nullable = false)
	private int salesPaymonth;
	//공급면적
	@Column(nullable = false)
	private int salesSupply;
	//전용면적
	@Column(nullable = false)
	private int salesExclusive;
	//건물층수
	@Column(nullable = false)
	private int salesAllFloors;
	//매물층수
	@Column(nullable = false)
	private int salesPositonFloor;
	//난방종류
	@Enumerated(EnumType.STRING)
	private BoilType salesBoilType;
	//입주가능일
	@Column(nullable = false)
	private DateTimeException salesDate;
	//관리비
	@Column
	private boolean salesMaintenance;
	//주차
	@Column
	private boolean salesParking;
	//엘리베이터
	@Column
	private boolean salesElevator;
	//빌트인
	@Column
	private boolean salesBuiltIn;
	
}
