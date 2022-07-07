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
@Entity(name = "VisualFile")
public class VisualFileEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long visualNo;
	@Column
	private String visualUrl;
	@Column
	private String visualOriginalName;
	@Column
	private String visualChangeName;
	@Column
	private String visualTitle;
	@Column
	private String visualSub;
	@Column
	private long visualSize;
}
