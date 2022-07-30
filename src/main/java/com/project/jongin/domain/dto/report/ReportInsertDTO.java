package com.project.jongin.domain.dto.report;

import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.entity.MemberEntity;
import com.project.jongin.domain.entity.ReportEntity;
import com.project.jongin.domain.enumes.ReportType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportInsertDTO {
	private String reportTitle;
	private String reportContents;
	private long memberNo;
	private long boardNo;
	private ReportType reportType;
	
	public ReportEntity toEntity() {
		return ReportEntity.builder()
				.reportTitle(reportTitle)
				.reportContents(reportContents)
				.reportType(reportType)
				.memberEntity(MemberEntity.builder().memberNo(memberNo).build())
				.boardEntity(BoardEntity.builder().boardNo(boardNo).build())
				.build();
	}
}
