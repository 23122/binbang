package com.project.jongin.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jongin.domain.entity.VisualFileEntity;

public interface VisualFileRepository extends JpaRepository<VisualFileEntity, Long>{
	List<VisualFileEntity> findAllByIsShowOrderByNum(boolean isShow);
}
