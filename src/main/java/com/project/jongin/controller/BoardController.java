package com.project.jongin.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.board.BoardInsertDTO;
import com.project.jongin.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/customer/board/map")
	public String list() {
		return "board/list";
	}
	@GetMapping("/customer/board/list")
	public String list(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		return boardService.list(pageNo,model);
	}
	@GetMapping("/customer/board/sum")
	public String sum(Model model) {
		return boardService.sum(model);
	}
	@PostMapping("/board/binbang/write")
	public String write(BoardInsertDTO dto,MultipartFile[] file) {
		return boardService.save(dto,file);
	}
	@GetMapping("/board/detail/{boardNo}")
	public String detail(@PathVariable long boardNo,Model model) {
		return boardService.detail(boardNo,model);
	}
	@ResponseBody
	@DeleteMapping("/board/detail/{boardNo}")
	public String delete(@PathVariable long boardNo) {
		return boardService.delete(boardNo);
	}
	@ResponseBody
	@PostMapping("/board/fileupload")
	public String fileUpload(MultipartFile file/* ,String prevImgName */) {
		return boardService.fileUpload(file/* ,prevImgName */);
	}
	@ResponseBody
	@PostMapping("/admin/uploadSummernoteImg")
	public String uploadSummernoteImg(MultipartFile file) {
		if(!file.getContentType().contains("image") ) return null;
		System.out.println("summernote ajax 실행");
		String url="/img/summernote/";
		ClassPathResource cpr=new ClassPathResource("static"+url);
		String orgName=file.getOriginalFilename();
		String saveName=UUID.randomUUID()+"_"+orgName;
		
		try {
			File location= cpr.getFile();
			file.transferTo(new File(location, saveName));			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url+saveName;
	}
	
}
