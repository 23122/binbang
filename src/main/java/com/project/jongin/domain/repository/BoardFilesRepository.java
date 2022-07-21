package com.project.jongin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.BoardFilesEntity;

@Repository
public interface BoardFilesRepository extends JpaRepository<BoardFilesEntity, Long>{

}
