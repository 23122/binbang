package com.project.jongin.service;

import org.springframework.ui.Model;

import com.project.jongin.domain.dto.sales.SalesInsertDTO;

public interface SalesService{

	String list(Model model);

	String save(SalesInsertDTO dto);

}
