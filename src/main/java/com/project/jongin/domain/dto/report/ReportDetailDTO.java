package com.project.jongin.domain.dto.report;

import com.project.jongin.domain.entity.ReportEntity;

import lombok.Getter;

@Getter
public class ReportDetailDTO {

	private long reportNo;
	private long memberNo;
	private long boardNo;
	private long boardMemberNo;
	private String boardMemberEmail;
	private String memberEmail;
	private String reportTitle;
	private String reportContents;
	private String boardTitle;
	
	
	public ReportDetailDTO(ReportEntity e) {
		reportNo=e.getReportNo();
		boardMemberNo=e.getBoardEntity().getMemberEntity().getMemberNo();
		boardMemberEmail=e.getBoardEntity().getMemberEntity().getMemberEmail();
		memberNo=e.getMemberEntity().getMemberNo();
		boardNo=e.getBoardEntity().getBoardNo();
		memberEmail=e.getMemberEntity().getMemberEmail();
		reportTitle=e.getReportTitle();
		reportContents=e.getReportContents();
		boardTitle=e.getBoardEntity().getBoardTitle();
	}
}
