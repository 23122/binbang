package com.project.jongin.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.board.BoardInsertDTO;

public interface BoardService {

	String fileUpload(MultipartFile file, String prevImgName);
	
	String list(Model model);

	String save(BoardInsertDTO dto);

//	String save(SalesInsertDTO dto, MultipartFile[] file);

}
