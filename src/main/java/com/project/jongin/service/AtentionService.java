package com.project.jongin.service;

import org.springframework.ui.Model;

import com.project.jongin.domain.dto.atention.AtentionInsertDTO;

public interface AtentionService {

	String save(AtentionInsertDTO dto);

	void delete(long boardNo);

	String list(long memberNo, int pageNo, Model model);

}
