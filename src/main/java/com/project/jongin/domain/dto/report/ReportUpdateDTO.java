package com.project.jongin.domain.dto.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReportUpdateDTO {
	private String reportTitle;
	private String reportContents;
}
