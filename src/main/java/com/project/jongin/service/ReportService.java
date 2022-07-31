package com.project.jongin.service;

import org.springframework.ui.Model;

import com.project.jongin.domain.dto.report.ReportInsertDTO;
import com.project.jongin.domain.dto.report.ReportUpdateDTO;


public interface ReportService {

	String listData(int cate,int pageNo, Model model);

	String list(int pageNo, Model model);

	String save(ReportInsertDTO dto);

	String update(long reportNo, ReportUpdateDTO dto);

	String detail(long reportNo, Model model);

	void delete(long reportNo);
	
}
