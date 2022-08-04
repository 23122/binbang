package com.project.jongin;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.project.jongin.domain.entity.MemberEntity;
import com.project.jongin.domain.entity.BoardEntity;
import com.project.jongin.domain.enumes.HouseType;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.PayType;
import com.project.jongin.domain.repository.MemberRepository;
import com.project.jongin.domain.repository.BoardRepository;
import com.project.jongin.securiy.MemberRole;


@SpringBootTest
class JoinJinApplicationTests {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	@Autowired
	BoardRepository salesRepository;
	
//	@Test
	void 관리자생성() {
		memberRepository.save(MemberEntity.builder()
				.memberEmail("")
				.memberPass(passEncoder.encode(""))
				.memberName("")
				.memberIp("127.0.0.225")
				.build().addMemberRole(MemberRole.ADMIN).addMemberRole(MemberRole.USER));
	}
//	@Test
	void 회원생성() {
		memberRepository.save(MemberEntity.builder()
				.memberEmail("")
				.memberPass(passEncoder.encode(""))
				.memberName("")
				.memberIp("127.0.0.225")
				.build().addMemberRole(MemberRole.USER));
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
//	@Test
	void 회원가입테스트() {
		memberRepository.save(MemberEntity.builder()
				.memberEmail("").memberPass("").memberName("").memberIp("")
				.build());
	}
	
	@Rollback(value = false)
	@Transactional
//	@Test
	void 회원수정테스트() {
		memberRepository.findById(2L).orElseThrow().memberName("이름변경").memberPass("");
	}

}
