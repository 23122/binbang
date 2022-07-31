package com.project.jongin.domain.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.AtentionEntity;

@Repository
public interface AtentionRepository extends JpaRepository<AtentionEntity, Long>{

	List<AtentionEntity> findByMemberEntityMemberNo(long memberNo);

	void deleteByBoardEntityBoardNo(long boardNo);

	List<AtentionEntity> findByMemberEntityMemberNo(long memberNo, Pageable pageable);

}
