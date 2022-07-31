package com.project.jongin.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "atention")
public class AtentionEntity extends BaseTimeEntity{
	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long atentionNo;
	@Column
	private boolean isAtention;
	
	@JoinColumn(name = "memberNo",nullable = false)
	@ManyToOne
	private MemberEntity memberEntity;
	
	@JoinColumn(name = "boardNo",nullable = false)
	@ManyToOne
	private BoardEntity boardEntity;
	
}
