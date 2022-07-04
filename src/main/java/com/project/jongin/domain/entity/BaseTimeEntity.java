package com.project.jongin.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updatedDate;
	
}
