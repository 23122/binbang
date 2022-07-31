package com.project.jongin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.jongin.domain.dto.atention.AtentionInsertDTO;
import com.project.jongin.domain.dto.atention.AtentionListPageDTO;
import com.project.jongin.domain.entity.AtentionEntity;
import com.project.jongin.domain.repository.AtentionRepository;
import com.project.jongin.service.AtentionService;
import com.project.jongin.utils.PageInfo;

@Service
public class AtentionServiceProc implements AtentionService{
	
	@Autowired
	AtentionRepository atentionRepository;
	
	@Transactional
	@Override
	public String save(AtentionInsertDTO dto) {
		atentionRepository.save(dto.toEntity());
		return null;
	}
	@Transactional
	@Override
	public void delete(long boardNo) {
		atentionRepository.deleteByBoardEntityBoardNo(boardNo);
	}
	@Override
	public String list(long memberNo,int pageNo, Model model) {
		int page = pageNo - 1;
		int size = 10;
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "atentionNo"));
		List<AtentionListPageDTO> result = atentionRepository.findByMemberEntityMemberNo(memberNo,pageable).stream().map(AtentionListPageDTO::new)
				.collect(Collectors.toList());
		Page<AtentionEntity> pageObj = atentionRepository.findAll(pageable);
		int pageTotal = pageObj.getTotalPages();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("atention", result);

		return "/board/atention/list";
	}

}
