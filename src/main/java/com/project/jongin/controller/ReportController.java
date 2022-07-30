package com.project.jongin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.jongin.domain.dto.report.ReportInsertDTO;
import com.project.jongin.domain.dto.report.ReportUpdateDTO;
import com.project.jongin.domain.entity.ReportEntity;
import com.project.jongin.service.ReportService;

@RequestMapping("/report")
@Controller
public class ReportController {
	
	@Autowired
	ReportService reportService;
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int pageNo,Model model) {
		return reportService.list(pageNo,model);
	}
	@GetMapping("/list/{cate}")
	public String list(@RequestParam(defaultValue = "1") int pageNo,Model model,@PathVariable int cate) {
		return reportService.listData(cate,pageNo,model);
	}
	@GetMapping("/write")
	public String write() {
		return "/board/report/write";
	}
	@PostMapping("/write/test")
	public String save(ReportInsertDTO dto) {
		ReportEntity re = dto.toEntity();
		System.out.println(re);
		return reportService.save(dto);
	}
	@PutMapping("/detail/update/{reportNo}")
	public String update(@PathVariable long reportNo,ReportUpdateDTO dto) {
		System.out.println("실행");
		return reportService.update(reportNo,dto);
	}
	@GetMapping("/detail/{reportNo}")
	public String detail(@PathVariable long reportNo,Model model) {
		return reportService.detail(reportNo,model);
	}
	@DeleteMapping("/detail/{reportNo}")
	public String delete(@PathVariable long reportNo) {
		return reportService.delete(reportNo);
	}
	
}
