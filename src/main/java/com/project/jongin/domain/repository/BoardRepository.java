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

	BoardEntity findAllByBoardHouseTypeOrderByBoardNoDesc(HouseType type);

	List<BoardEntity> findByBoardPayTypeOrderByBoardNoDesc(PayType type);

	List<BoardEntity> findByBoardPayTypeAndBoardBuildTypeOrderByBoardNoDesc(PayType pType, BuildType bType);

	List<BoardEntity> findByBoardPayTypeAndBoardBuildTypeAndBoardDepositBetweenOrderByBoardNoDesc(PayType pType, BuildType bType,
			int price1, int price2);

	List<BoardEntity> findByBoardPayTypeAndBoardBuildTypeAndBoardDepositBetweenAndBoardPaymonthBetweenOrderByBoardNoDesc(
			PayType pType, BuildType bType, int price1, int price2, int monthPrice1, int monthPrice2);

	List<BoardEntity> findByBoardAddressContainingAndBoardPayTypeAndBoardBuildTypeAndBoardDepositBetweenAndBoardPaymonthBetweenOrderByBoardNoDesc(
			String address, PayType pType, BuildType bType, int price1, int price2, int monthPrice1, int monthPrice2);


}
