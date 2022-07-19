package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PayType {
	MONTH("월세"),
	LEASE("전세"),
	DEAL("매매");
	
	final String title;
}
