package com.project.jongin.domain.dto.atention;

import com.project.jongin.domain.entity.AtentionEntity;

import lombok.Getter;

@Getter
public class AtentionListDTO {
	private long memberNo;
	private boolean isAtention;
	private long boardNo;
	
	public AtentionListDTO(AtentionEntity e) {
		memberNo=e.getMemberEntity().getMemberNo();
		isAtention=e.isAtention();
		boardNo=e.getBoardEntity().getBoardNo();
	}
}
