package com.project.jongin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.board.BoardInsertDTO;
import com.project.jongin.domain.entity.FileEntity;
import com.project.jongin.domain.repository.FileRepository;
import com.project.jongin.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/customer/board")
	public String list() {
		return "board/list";
	}
	@GetMapping("/customer/board/list")
	public String list(Model model) {
		return boardService.list(model);
	}
	@PostMapping("/board/binbang/write")
	public String write(BoardInsertDTO dto) {
		return boardService.save(dto);
	}
	@GetMapping("/board/list/{boardNo}")
	public String detail(@PathVariable long boardNo,Model model) {
		return boardService.detail(boardNo,model);
	}
	@ResponseBody//성공시 문자열 리턴-> ajax success
	@PostMapping("/board/fileupload")
	public String fileUpload(MultipartFile file,String prevImgName) {
		return boardService.fileUpload(file,prevImgName);
	}
//	@PostMapping("/boardjpa/write")
//	public String write(SalesInsertDTO dto,MultipartFile[] file) {
//		return service.save(dto,file);
//	}
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@ResponseBody
	@GetMapping(value =  "/board/files/{fileNo}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@PathVariable(value = "fileNo") long fileNo,@RequestHeader("User-Agent") String userAgent)throws Exception {
		if(userAgent.contains("Edg")) {
			System.out.println("엣지브라우저");
		}else {
			System.out.println("크롬브라우저");
		}
		FileEntity entity =fileRepository.findById(fileNo).orElseThrow();
		
		Resource resource=resourceLoader.getResource("classpath:static/upload/"+entity.getFileChangeName());
		String fileName=new String(entity.getFileOriginalName().getBytes("UTF-8"),"ISO-8859-1");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
				.header(HttpHeaders.CONTENT_LENGTH,entity.getFileSize()+"")
				.body(resource);
	}
}
