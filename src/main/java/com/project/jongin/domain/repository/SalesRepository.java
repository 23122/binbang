package com.project.jongin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.SalesEntity;
import com.project.jongin.domain.enumes.BinbangType;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long>{

	SalesEntity findAllBySalesType(BinbangType type);


}
