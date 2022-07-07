package com.project.jongin.domain.dto.visual;

import com.project.jongin.domain.dto.FileData;
import com.project.jongin.domain.entity.VisualFileEntity;

import lombok.Setter;

@Setter
public class VisualInsertDTO extends FileData{
	private String visualTitle;
	private String visualSub;
	
	public VisualFileEntity toVisualFile() {
		return VisualFileEntity.builder()
				.visualTitle(visualTitle)
				.visualUrl(fileUrl)
				.visualOriginalName(fileOriginalName)
				.visualSize(fileSize)
				.visualSub(visualSub)
				.build();
	}

	public void addFileData(FileData fileData) {
		fileUrl=fileData.getFileUrl();
		fileOriginalName=fileData.getFileOriginalName();
		fileSize=fileData.getFileSize();
	}
	
}
