package com.project.jongin.service;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

	String fileUpload(MultipartFile file, String prevImgName);

}
