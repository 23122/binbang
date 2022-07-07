package com.project.jongin.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.FileData;
import com.project.jongin.domain.dto.visual.VisualInsertDTO;
import com.project.jongin.domain.dto.visual.VisualLsitDto;
import com.project.jongin.domain.repository.VisualFileRepository;
import com.project.jongin.service.VisualService;
import com.project.jongin.utils.MyFileUtils;

@Service
public class VisualServiceProc implements VisualService{
	
	@Autowired
	private VisualFileRepository visualFileRepository;
	
	@Override
	public String save(MultipartFile visualImg, VisualInsertDTO dto) {
		
		//파일업로드
		String visualUrl="/img/visual/";//업로드할 서버위치
		FileData fileData = MyFileUtils.upload(visualImg,visualUrl);
		dto.addFileData(fileData);
		//db저장
		visualFileRepository.save(dto.toVisualFile());
		return "redirect:/admin/visuals";
	}

	@Override
	public String list(Model model) {
		model.addAttribute("list",visualFileRepository.findAll().stream().map(VisualLsitDto::new).collect(Collectors.toList()));
		return "/admin/visual/list";
	}

}
