package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OptionType {
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
	BIDET("비데");
	final String title;
}
