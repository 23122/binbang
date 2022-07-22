package com.project.jongin.domain.enumes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BuildType {
	DETACH("단독주택"),
	MULTI("다가구주택"),
	VILLA("빌라/연립/다가구"),
	STORE("상가");
	
	final String title;
	public String title() {
		return title;
	}
	
}
