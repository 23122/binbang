package com.project.jongin.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.FileData;

public class MyFileUtils {

	public static FileData upload(MultipartFile multipartFile, String url) {
		String fileOriginalName=multipartFile.getOriginalFilename();
		String fileChangeName=null;
		long fileSize=multipartFile.getSize();
		
		ClassPathResource cpr= new ClassPathResource("static" + url);
		
		try {
			File location=cpr.getFile();
			multipartFile.transferTo(new File(location,fileOriginalName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return FileData.builder()
				.fileUrl(url)
				.fileOriginalName(fileOriginalName)
				.fileSize(fileSize)
				.fileChangeName(fileChangeName)
				.build();
	}

}
