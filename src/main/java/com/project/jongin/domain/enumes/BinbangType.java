package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BinbangType {
	//주거타입
	ONEROOM("원룸"),TWOROOM("투룸"),THREEROOM("쓰리룸"),APT("아파트"),OFFICETEL("오피스텔"),
	//난방타입
	EACH("개별난방"),CENTER("중앙난방"),LOCAL("지역난방"),ETC("기타"),
	//주거형태
	DETACH("단독주택"),MULTI("다가구주택"),VILLA("빌라/연립/다가구"),STORE("상가"),
	//옵션
	AIR("에어컨"),WASH("세탁기"),BED("침대"),DEST("책상"),CLOTHES("옷장"),TV("TV"),SHOES("신발장"),FRIDEGE("냉장고"),
	GAS("가스레인지"),INDUCTION("인덕션"),MICROWAVE("전자레인지"),DOORLOCK("전자도어락"),BIDET("비데"),
	//매매타입
	MONTH("월세"),LEASE("전세"),DEAL("매매");
	
	final String title;
}
