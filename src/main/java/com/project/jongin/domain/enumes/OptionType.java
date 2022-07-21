package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OptionType {
	//옵션
	MAINTENANCE("관리비"),
	PARKING("주차장"),
	ELEVATOR("엘리베이터"),
	BUILTIN("빌트인"),
	//추가옵션
	AIR("에어컨"),
	WASH("세탁기"),
	BED("침대"),
	DEST("책상"),
	CLOTHES("옷장"),
	TV("TV"),
	SHOES("신발장"),
	FRIDEGE("냉장고"),
	GAS("가스레인지"),
	INDUCTION("인덕션"),
	MICROWAVE("전자레인지"),
	DOORLOCK("전자도어락"),
	BIDET("비데"),
	//난방
	EACH("개별난방"),
	CENTER("중앙난방"),
	LOCAL("지역난방"),
	ETC("기타");
	final String title;
}
