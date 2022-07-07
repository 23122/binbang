package com.project.jongin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileData {
	
	protected String fileUrl;
	protected String fileOriginalName;
	protected String fileChangeName;
	protected long fileSize;
}
