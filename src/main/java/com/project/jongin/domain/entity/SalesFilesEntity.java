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
@Entity(name = "salesFiles")
public class SalesFilesEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long salesFilesNo;
	@Column(nullable = false)
	private String salesFilesUrl;
	@Column(nullable = false)
	private String salesFilesOriginalName;
	@Column(nullable = false)
	private String salesFilesChangeName;
	@Column(nullable = false)
	private long salesFilesSize;
	@Column
	private boolean isMain;
	
}
