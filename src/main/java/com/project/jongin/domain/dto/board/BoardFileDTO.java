package com.project.jongin.domain.dto.board;

import com.project.jongin.domain.entity.BoardFilesEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardFileDTO {
	private long boardFilesNo;
	private String boardFilesUrl;
	private String boardFilesOriginalName;
	private String boardFilesChangeName;
	private long boardFilesSize;
	private boolean isMain;
	
	public BoardFileDTO(BoardFilesEntity e) {
		boardFilesNo=e.getBoardFilesNo();
		boardFilesUrl=e.getBoardFilesUrl();
		boardFilesOriginalName=e.getBoardFilesOriginalName();
		boardFilesChangeName=e.getBoardFilesChangeName();
		boardFilesSize=e.getBoardFilesSize();
	}
}
