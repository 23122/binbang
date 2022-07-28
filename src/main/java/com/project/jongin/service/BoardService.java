package com.project.jongin.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.board.BoardInsertDTO;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.PayType;

public interface BoardService {

	String fileUpload(MultipartFile file/* , String prevImgName */);
	
	String list(int pageNo, Model model);

	String save(BoardInsertDTO dto, MultipartFile[] file);

	String detail(long boardNo, Model model);

	String delete(long boardNo);

	String sum(Model model);

	String sumCate1(int payType, Model model);

	String mapList(Model model);

	String sumCate2(PayType pType, int cate, Model model);

	String sumCate3(PayType pType, BuildType bType, int price1, int price2, Model model);

	String sumCate4(PayType pType, BuildType bType, int price1, int price2, int monthPrice1, int monthPrice2,
			Model model);

//	String save(SalesInsertDTO dto, MultipartFile[] file);

}
