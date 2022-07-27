package com.project.jongin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.board.BoardDetailDTO;
import com.project.jongin.domain.dto.board.BoardInsertDTO;
import com.project.jongin.domain.dto.board.BoardListDTO;
import com.project.jongin.domain.dto.board.BoardMapDTO;
import com.project.jongin.domain.dto.board.BoardSumDTO;
import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.entity.BoardFilesEntity;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.PayType;
import com.project.jongin.domain.repository.BoardRepository;
import com.project.jongin.service.BoardService;
import com.project.jongin.utils.PageInfo;

@Service
public class BoardServiceProc implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public String list(int pageNo, Model model) {
		int page = pageNo - 1;
		int size = 10;
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "boardNo"));
		Page<BoardEntity> pageObj = boardRepository.findAll(pageable);
		int pageTotal = pageObj.getTotalPages();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list", pageObj.getContent().stream().map(BoardListDTO::new).collect(Collectors.toList()));

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
		BoardEntity entity = dto.toEntity();
		int i = 0;
		for (MultipartFile f : file) {
			if (!f.isEmpty()) {
				// bin경로
				String url = "/img/board/";
				ClassPathResource cprTemp = new ClassPathResource("static" + url + "temp");
				String fileStr[] = dto.getBoardFilesChangeName().split(",");
				try {

					File newFile = new File(cprTemp.getFile(), dto.getBoardFilesChangeName());
					ClassPathResource uploadDir = new ClassPathResource("static" + url);
					File dest = new File(uploadDir.getFile(), dto.getBoardFilesChangeName());
					newFile.renameTo(dest);
					entity.addFile(BoardFilesEntity.builder().boardFilesUrl(url).boardFilesSize(f.getSize())
							.boardFilesOriginalName(f.getOriginalFilename()).boardFilesChangeName(fileStr[i]).build());

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
		// 이름공백제거
		String boardFilesChangeName = filesChangeName.replaceAll(" ", "");
		// bin경로
		String url = "/img/board/temp/";
		ClassPathResource cpr = new ClassPathResource("static" + url);
		try {
			File location = cpr.getFile();
			// File prevImg = new File(location,prevImgName);
			// if(prevImg.isFile())prevImg.delete();
			file.transferTo(new File(location, boardFilesChangeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return boardFilesChangeName;
	}
	
	@Override
	public String sum(Model model) {
		List<BoardSumDTO> result = boardRepository.findByBoardPayType(PayType.MONTH).stream().map(BoardSumDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("list", result);
		return "/board/sumType/list";
	}
	@Override
	public String sumCate1(int payType, Model model) {
		System.out.println(payType);
		PayType pType=PayType.MONTH;
		if(payType!=0) {
			if(payType==1) {
				pType=PayType.LEASE;
			}else {
				pType=PayType.DEAL;
			}
		}
		System.out.println(pType);
		List<BoardSumDTO> result = boardRepository.findByBoardPayType(pType).stream().map(BoardSumDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("cate1", pType);
		model.addAttribute("list", result);
		return "/board/sumType/listType";
	}

	@Override
	public String sumCate2(PayType pType, int cate, Model model) {
		BuildType bType=null;
		switch (cate) {
		case 0:
			bType=BuildType.DETACH;
			break;
		case 1:
			bType=BuildType.MULTI;
			break;
		case 2:
			bType=BuildType.VILLA;
			break;
		case 3:
			bType=BuildType.APT;
			break;
		case 4:
			bType=BuildType.STORE;
			break;
		}
		System.out.println(bType);
		List<BoardSumDTO> result = boardRepository.findByBoardPayTypeAndBoardBuildType(pType,bType).stream().map(BoardSumDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("cate2", bType);
		model.addAttribute("cate1", pType);
		model.addAttribute("list", result);
		return "/board/sumType/listType";
	}

	@Override
	public String mapList(Model model) {
		List<BoardMapDTO> result=  boardRepository.findAll().stream().map(BoardMapDTO::new).collect(Collectors.toList());
		model.addAttribute("list",result);
		return "board/list";
	}

}
