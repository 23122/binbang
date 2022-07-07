package com.project.jongin.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.visual.VisualInsertDTO;

public interface VisualService {

	String save(MultipartFile vimg, VisualInsertDTO dto);

	String list(Model model);

}
