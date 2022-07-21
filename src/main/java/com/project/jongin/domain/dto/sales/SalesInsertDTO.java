package com.project.jongin.domain.dto.sales;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.jongin.domain.entity.SalesEntity;
import com.project.jongin.domain.entity.SalesFilesEntity;
import com.project.jongin.domain.enumes.BinbangType;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.OptionType;
import com.project.jongin.domain.enumes.PayType;

import lombok.Setter;

@Setter
public class SalesInsertDTO {
	private long salesNo;
	private BinbangType salesType;
	private BuildType salesBuildType;
	private String salesAddress;
	private String salesAddressDetail;
	private PayType salesPayType;
	private int salesDeposit;
	private int salesPaymonth;
	private int salesSupply;
	private int salesExclusive;
	private int salesAllFloors;
	private int salesPositonFloor;
	private String salesDate;
	private Set<OptionType> salesOptionType=new HashSet<>();
	private String salesTitle;
	private String salesContents;
	private String salesHiddenMemo;
	private List<SalesFilesEntity> salesFiles = new ArrayList<>();
	
	public SalesEntity toEntity() {
		return SalesEntity.builder()
				.salesNo(salesNo)
				.salesType(salesType)
				.salesBuildType(salesBuildType)
				.salesAddress(salesAddress)
				.salesPayType(salesPayType)
				.salesDeposit(salesDeposit)
				.salesPaymonth(salesPaymonth)
				.salesSupply(salesSupply)
				.salesExclusive(salesExclusive)
				.salesAllFloors(salesAllFloors)
				.salesPositonFloor(salesPositonFloor)
				.salesDate(salesDate)
				.salesOptionType(salesOptionType)
				.salesTitle(salesTitle)
				.salesContents(salesContents)
				.salesHiddenMemo(salesHiddenMemo)
				.salesFiles(salesFiles)
				.salesAddressDetail(salesAddressDetail)
				.build();
	}
}
