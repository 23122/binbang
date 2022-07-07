package com.project.jongin.domain.dto.visual;

import com.project.jongin.domain.entity.VisualFileEntity;

import lombok.Getter;

@Getter
public class VisualLsitDto {
	private long visualNo;
	private String visualUrl;
	private String visualOriginalName;
	private String visualChangeName;
	private String visualTitle;
	private String visualSub;
	private long visualSize;
	
	public VisualLsitDto(VisualFileEntity e) {
		super();
		this.visualNo = e.getVisualNo();
		this.visualUrl = e.getVisualUrl();
		this.visualOriginalName = e.getVisualOriginalName();
		this.visualChangeName = e.getVisualChangeName();
		this.visualTitle = e.getVisualTitle();
		this.visualSub = e.getVisualSub();
		this.visualSize = e.getVisualSize();
	}
	
}
