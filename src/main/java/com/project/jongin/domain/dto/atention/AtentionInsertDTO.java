package com.project.jongin.domain.dto.atention;

import com.project.jongin.domain.entity.AtentionEntity;
import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AtentionInsertDTO {
	private long memberNo;
	private boolean isAtention;
	private long boardNo;
	
	public AtentionEntity toEntity() {
		return AtentionEntity.builder()
				.memberEntity(MemberEntity.builder().memberNo(memberNo).build())
				.boardEntity(BoardEntity.builder().boardNo(boardNo).build())
				.isAtention(isAtention)
				.build();
		
	}
}
