package com.project.jongin.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.jongin.domain.dto.report.ReportUpdateDTO;
import com.project.jongin.domain.enumes.ReportType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "report")
public class ReportEntity extends BaseTimeEntity{
	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long reportNo;
	//제목
	@Column(nullable = false)
	private String reportTitle;
	//내용
	@Column(columnDefinition = "text not null")
	private String reportContents;
	//카테고리
	@Enumerated(EnumType.STRING)
	private ReportType reportType;
	
	@JoinColumn(name = "memberNo",nullable = false)
	@ManyToOne
	private MemberEntity memberEntity;
	
	@JoinColumn(name = "boardNo",nullable = false)
	@ManyToOne
	private BoardEntity boardEntity;

	public ReportEntity update(ReportUpdateDTO dto) {
		this.reportTitle=dto.getReportTitle();
		this.reportContents=dto.getReportContents();
		return this;
	}
	
}
