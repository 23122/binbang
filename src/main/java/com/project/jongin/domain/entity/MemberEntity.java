package com.project.jongin.domain.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.project.jongin.securiy.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Member")
public class MemberEntity extends BaseTimeEntity{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long memberNo;
	@Column(nullable = false,unique = true)
	private String memberEmail;
	@Column(nullable = false)
	private String memberPass;
	@Column(nullable = false)
	private String memberName;
	@Column(nullable = false)
	private String memberIp;
	@Column
	private boolean memberAbsence;//true : 탈퇴회원
	@Column
	private boolean memberSocial;//true : 소셜회원
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB데이터타입
	@ElementCollection(fetch = FetchType.EAGER)//동시처리
	private Set<MemberRole> roleSet=new HashSet<>();
	
	@Builder.Default
	@OneToMany(mappedBy = "memberEntity")
	private List<BoardEntity> boardEntities=new Vector<BoardEntity>();
	
	@Builder.Default
	@OneToMany(mappedBy = "memberEntity")
	private List<ReportEntity> reportEntities=new Vector<ReportEntity>();
	
	@Builder.Default
	@OneToMany(mappedBy = "memberEntity")
	private List<AtentionEntity> atentionEntitise=new Vector<AtentionEntity>();
	
	public MemberEntity memberPass(String memberPass) {
		this.memberPass=memberPass;
		return this;
	}
	public MemberEntity memberName(String memberName) {
		this.memberName=memberName;
		return this;
	}
	public MemberEntity addMemberRole(MemberRole role) {
		roleSet.add(role);
		return this;
	}
	public MemberEntity removeMemberRole(MemberRole role) {
		roleSet.remove(role);
		return this;
	}
	public MemberEntity socialUpdate(String name, String pass) {
		memberName=name;
		memberPass=pass;
		return this;
	}
}
