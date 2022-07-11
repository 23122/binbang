package com.project.jongin.domain.dto.visual;

import com.project.jongin.domain.entity.VisualFileEntity;

import lombok.Getter;

@Getter
public class VisualLsitDTO {
	private long visualNo;
	private String visualUrl;
	private String visualOriginalName;
	private String visualChangeName;
	private String visualTitle;
	private String visualSub;
	private String visualLink;
	private long visualSize;
	private int num;
	private boolean isShow;
	
	public VisualLsitDTO(VisualFileEntity e) {
		super();
		this.visualNo = e.getVisualNo();
		this.visualUrl = e.getVisualUrl();
		this.visualOriginalName = e.getVisualOriginalName();
		this.visualChangeName = e.getVisualChangeName();
		this.visualTitle = e.getVisualTitle();
		this.visualSub = e.getVisualSub();
		this.visualSize = e.getVisualSize();
		this.visualLink = e.getVisualLink();
		this.num=e.getNum();
		this.isShow=e.isShow();
	}
	
}
