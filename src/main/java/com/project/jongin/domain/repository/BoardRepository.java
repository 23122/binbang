package com.project.jongin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.enumes.HouseType;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	BoardEntity findAllByBoardHouseType(HouseType type);

}
