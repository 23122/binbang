package com.project.jongin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.jongin.domain.dto.board.BoardDetailDTO;
import com.project.jongin.domain.dto.report.ReportDetailDTO;
import com.project.jongin.domain.dto.report.ReportInsertDTO;
import com.project.jongin.domain.dto.report.ReportListDTO;
import com.project.jongin.domain.dto.report.ReportUpdateDTO;
import com.project.jongin.domain.entity.ReportEntity;
import com.project.jongin.domain.enumes.ReportType;
import com.project.jongin.domain.repository.ReportRepository;
import com.project.jongin.service.ReportService;
import com.project.jongin.utils.PageInfo;

@Service
public class ReportServiceProc implements ReportService{
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Override
	public String list(int pageNo, Model model) {
		int page = pageNo - 1;
		int size = 10;
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "reportNo"));
		Page<ReportEntity> pageObj = reportRepository.findAll(pageable);
		int pageTotal = pageObj.getTotalPages();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("cate1", 0);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list", pageObj.getContent().stream().map(ReportListDTO::new).collect(Collectors.toList()));
		
		return "/board/report/list";
	}
	
	@Override
	public String listData(int cate,int pageNo, Model model) {
		ReportType rType=null;
		switch (cate) {
		case 0:
			rType=ReportType.PRICE;
			break;
		case 1:
			rType=ReportType.FILE;
			break;
		case 2:
			rType=ReportType.INFO;
			break;
		case 3:
			rType=ReportType.ECT;
			break;
		}
		
		int page = pageNo - 1;
		int size = 10;
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "reportNo"));
		List<ReportListDTO> result = reportRepository.findByReportType(rType,pageable).stream().map(ReportListDTO::new)
				.collect(Collectors.toList());
		Page<ReportEntity> pageObj = reportRepository.findAll(pageable);
		int pageTotal = pageObj.getTotalPages();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("cate1", rType);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list", result);

		return "/board/report/list-data";
	}
	@Transactional
	@Override
	public String save(ReportInsertDTO dto) {
		ReportEntity re = dto.toEntity();
		System.out.println(re);
		reportRepository.save(dto.toEntity());
		return "redirect:/report/list";
	}

	@Override
	public String detail(long reportNo, Model model) {
		model.addAttribute("detail", reportRepository.findById(reportNo).map(ReportDetailDTO::new).get());
		return "/board/report/detail";
	}
	
	@Transactional
	@Override
	public String update(long reportNo, ReportUpdateDTO dto) {
		System.out.println("실행");
		System.out.println(reportNo);
		System.out.println(dto);
		reportRepository.findById(reportNo).map(e->e.update(dto));
		return "redirect:/report/detail/"+reportNo;
	}

	@Override
	public String delete(long reportNo) {
		reportRepository.deleteById(reportNo);
		return "redirect:/report/list";
	}

	

}
