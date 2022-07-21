package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HouseType {

	ONEROOM("원룸"),
	TWOROOM("투룸"),
	THREEROOM("쓰리룸"),
	APT("아파트"),
	OFFICETEL("오피스텔");
	
	final String title;
}
