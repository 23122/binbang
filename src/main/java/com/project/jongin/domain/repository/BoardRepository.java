package com.project.jongin.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.HouseType;
import com.project.jongin.domain.enumes.PayType;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	BoardEntity findAllByBoardHouseType(HouseType type);

	List<BoardEntity> findByBoardPayType(PayType type);

	List<BoardEntity> findByBoardPayTypeAndBoardBuildType(PayType pType, BuildType bType);


}
