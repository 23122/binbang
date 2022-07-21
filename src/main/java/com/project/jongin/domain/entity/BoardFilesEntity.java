package com.project.jongin.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "boardFiles")
public class BoardFilesEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long boardFilesNo;
	@Column(nullable = false)
	private String boardFilesUrl;
	@Column(nullable = false)
	private String boardFilesOriginalName;
	@Column(nullable = false)
	private String boardFilesChangeName;
	@Column(nullable = false)
	private long boardFilesSize;
	@Column
	private boolean isMain;
	
}
