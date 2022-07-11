package com.project.jongin.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.FileData;
import com.project.jongin.domain.dto.visual.VisualInsertDTO;
import com.project.jongin.domain.dto.visual.VisualLsitDTO;
import com.project.jongin.domain.dto.visual.VisualUpdateDTO;
import com.project.jongin.domain.entity.VisualFileEntity;
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
		model.addAttribute("list",visualFileRepository.findAll()
				.stream().map(VisualLsitDTO::new).collect(Collectors.toList()));
		return "/admin/visual/list";
	}

	@Override
	public String indexList(Model model) {
		model.addAttribute("list",visualFileRepository.findAllByIsShowOrderByNum(true)
				.stream().map(VisualLsitDTO::new).collect(Collectors.toList()));
		return "/visual/list";
	}

	@Override
	public boolean updateData(long visualNo, VisualUpdateDTO dto) {
		Optional<VisualFileEntity> result=visualFileRepository.findById(visualNo);
		if(result.isEmpty())return false;
		visualFileRepository.save(result.get().updateData(dto));
		return true;
	}

	@Override
	public boolean updateIsShow(long visualNo, boolean isShow) {
		Optional<VisualFileEntity> result=visualFileRepository.findById(visualNo);
		if(result.isEmpty())return false;
		visualFileRepository.save(result.get().updateIsShow(isShow));
		return true;
	}

}
