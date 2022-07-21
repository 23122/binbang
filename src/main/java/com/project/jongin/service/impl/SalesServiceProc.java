package com.project.jongin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.jongin.domain.dto.sales.SalesInsertDTO;
import com.project.jongin.domain.enumes.BinbangType;
import com.project.jongin.domain.repository.SalesRepository;
import com.project.jongin.service.SalesService;

@Service
public class SalesServiceProc implements SalesService{
	
	@Autowired
	private SalesRepository salesRepository;
	
	@Override
	public String list(Model model) {
		BinbangType type=BinbangType.APT;
		salesRepository.findAllBySalesType(type);
		return "/board/apt/list";
	}

	@Override
	public String save(SalesInsertDTO dto) {
		salesRepository.save(dto.toEntity());
		return "/board/apt/list";
	}

}
