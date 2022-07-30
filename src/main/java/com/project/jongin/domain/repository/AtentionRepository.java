package com.project.jongin.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.ReportEntity;

@Repository
public interface AtentionRepository extends JpaRepository<ReportEntity, Long>{

}
