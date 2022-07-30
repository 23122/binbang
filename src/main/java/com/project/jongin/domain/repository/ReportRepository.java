package com.project.jongin.domain.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jongin.domain.entity.ReportEntity;
import com.project.jongin.domain.enumes.ReportType;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long>{

	List<ReportEntity> findByReportType(ReportType rType, Pageable pageable);


}
