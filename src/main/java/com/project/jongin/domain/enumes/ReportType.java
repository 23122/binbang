package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReportType {
	PRICE("허위가격","0"),
	FILE("사진상이","1"),
	INFO("정보오류","2"),
	ECT("기타","3");
   	
	final String title;
	public String title() {
		return title;
	}
	final String no;
	public String no() {
		return no;
	}
}
