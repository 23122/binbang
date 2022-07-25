package com.project.jongin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.board.BoardDetailDTO;
import com.project.jongin.domain.dto.board.BoardInsertDTO;
import com.project.jongin.domain.dto.board.BoardListDTO;
import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.entity.BoardFilesEntity;
import com.project.jongin.domain.repository.BoardRepository;
import com.project.jongin.service.BoardService;

@Service
public class BoardServiceProc implements BoardService{
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public String list(Model model) {
		List<BoardListDTO> result=boardRepository.findAll() /*List<JpaBoardEntity>*/
				.stream() /*Stream<BoardEntity>*/
				.map(BoardListDTO::new) /*.map(e-> BoardListDTO(e))*/
				.collect(Collectors.toList());
		model.addAttribute("list", result);
		return "/board/listType/list";
	}
	
	@Override
	public String detail(long boardNo, Model model) {
		model.addAttribute("detail", boardRepository.findById(boardNo).map(BoardDetailDTO::new).get());
		return "/board/listType/detail";
	}

	@Override
	public String delete(long boardNo) {
		boardRepository.deleteById(boardNo);
		return "redirect:/customer/board/list";
	}
	
	@Transactional
	@Override
	public String save(BoardInsertDTO dto, MultipartFile[] file) {
		System.out.println(">>>>>>>>>>>>>>>>" + file);
		BoardEntity entity=dto.toEntity();
		int i=0;
		for (MultipartFile f : file) {
			if (!f.isEmpty()) {
				//bin경로
				String url = "/img/board/";
				ClassPathResource cprTemp = new ClassPathResource("static" + url+"temp");
				String fileStr[]=dto.getBoardFilesChangeName().split(",");
				try {
					
					File newFile=new File(cprTemp.getFile(), dto.getBoardFilesChangeName());
					ClassPathResource uploadDir = new ClassPathResource("static" + url);
					File dest=new File (uploadDir.getFile(),dto.getBoardFilesChangeName());
					newFile.renameTo(dest);
					entity.addFile(BoardFilesEntity.builder()
							.boardFilesUrl(url)
							.boardFilesSize(f.getSize())
							.boardFilesOriginalName(f.getOriginalFilename())
							.boardFilesChangeName(fileStr[i])
							.build());
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
			}
			i++;
		}
		
		boardRepository.save(entity);
		return "redirect:/customer/board/list";
	}
	
	@Override
	public String fileUpload(MultipartFile file/* , String prevImgName */) {
		
		String boardFilesOriginalName = file.getOriginalFilename();
		// 이름중복처리
		String filesChangeName = System.nanoTime() + "_" + boardFilesOriginalName;
		//이름공백제거
		String boardFilesChangeName=filesChangeName.replaceAll(" " , "");
		// bin경로
		String url = "/img/board/temp/";
		ClassPathResource cpr = new ClassPathResource("static" + url);
		try {
			File location=cpr.getFile();
			//File prevImg = new File(location,prevImgName);
			//if(prevImg.isFile())prevImg.delete();
			file.transferTo(new File(location, boardFilesChangeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return boardFilesChangeName;
	}

}
