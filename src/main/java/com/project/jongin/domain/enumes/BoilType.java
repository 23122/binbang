package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BoilType {
	EACH("개별난방"),
	CENTER("중앙난방"),
	LOCAL("지역난방"),
	ETC("기타");
	
	final String title;
}
