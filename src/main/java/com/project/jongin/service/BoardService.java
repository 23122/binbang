package com.project.jongin.service;

import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.sales.SalesInsertDTO;

public interface BoardService {

	String fileUpload(MultipartFile file, String prevImgName);

//	String save(SalesInsertDTO dto, MultipartFile[] file);

}
