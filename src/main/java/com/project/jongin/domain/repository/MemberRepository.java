package com.project.jongin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jongin.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findByMemberEmail(String memberEmail);
	
	Optional<MemberEntity> findByMemberEmailAndMemberAbsence(String memberEmail,boolean memberAbsence);

}
