package com.project.jongin.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.visual.VisualInsertDTO;
import com.project.jongin.domain.dto.visual.VisualUpdateDTO;

public interface VisualService {

	String save(MultipartFile vimg, VisualInsertDTO dto);

	String list(Model model);

	String indexList(Model model);

	boolean updateData(long visualNo, VisualUpdateDTO dto);

	boolean updateIsShow(long visualNo, boolean isShow);

}
