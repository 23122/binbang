package com.project.jongin.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.sales.SalesInsertDTO;
import com.project.jongin.domain.entity.SalesEntity;
import com.project.jongin.service.BoardService;

@Service
public class BoardServiceProc implements BoardService{
	
	
	
	@Override
	public String fileUpload(MultipartFile file,String prevImgName) {
		String salesFilesOriginalName = file.getOriginalFilename();
		// 이름중복처리
		String salesFilesChangeName = System.nanoTime() + "_" + salesFilesOriginalName;
		// bin경로
		String url = "/img/temp/";
		ClassPathResource cpr = new ClassPathResource("static" + url);
		try {
			File location=cpr.getFile();
			File prevImg = new File(location,prevImgName);
			if(prevImg.isFile())prevImg.delete();
			file.transferTo(new File(location, salesFilesChangeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return salesFilesChangeName;
	}
	
//	@Transactional
//	@Override
//	public String save(SalesInsertDTO dto, MultipartFile[] file) {
//		System.out.println(">>>>>>>>>>>>>>>>" + file);
//		SalesEntity entity=dto.toEntity();
//		for (MultipartFile f : file) {
//			if (!f.isEmpty()) {
//				
//				//src경로
//				//String url2 = "E:/java/spring/baekhwa-project/src/main/resources/static/upload";
//			
//				//lunux 이용시
//				//String linux = "/home/ec2-user/src/root";
//			
//				//bin경로
//				String url = "/upload/";
//				ClassPathResource cprTemp = new ClassPathResource("static" + url+"temp");
//
//				try {
//					File newFile=new File(cprTemp.getFile(), dto.getFileChangeName());
//					ClassPathResource uploadDir = new ClassPathResource("static" + url);
//					File dest=new File (uploadDir.getFile(),dto.getFileChangeName());
//					newFile.renameTo(dest);
//					entity.addFile(FileEntity.builder()
//							.fileUrl(url)
//							.fileSize(f.getSize())
//							.fileOriginalName(f.getOriginalFilename())
//							.fileChangeName(dto.getFileChangeName())
//							.build());
//				} catch (IllegalStateException | IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		repository.save(entity);
//		return "redirect:/boardjpa";
//	}
//
}
