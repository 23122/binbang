package com.project.jongin.domain.dto.report;

import java.time.LocalDateTime;

import com.project.jongin.domain.entity.ReportEntity;
import com.project.jongin.domain.enumes.ReportType;

import lombok.Getter;

@Getter
public class ReportListDTO {
	private long reportNo;
	private String reportTitle;
	private String reportContents;
	private String memberEmail;
	private long boardNo;
	private String boardTitle;
	private ReportType reportType;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public  ReportListDTO(ReportEntity e) {
		reportNo=e.getReportNo();
		reportTitle=e.getReportTitle();
		reportContents=e.getReportContents();
		memberEmail=e.getMemberEntity().getMemberEmail();
		boardNo=e.getBoardEntity().getBoardNo();
		boardTitle=e.getBoardEntity().getBoardTitle();
		reportType= e.getReportType();
		createdDate=e.getCreatedDate();
		updatedDate=e.getUpdatedDate();
	}
}
