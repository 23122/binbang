package com.project.jongin;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.project.jongin.domain.entity.MemberEntity;
import com.project.jongin.domain.entity.SalesEntity;
import com.project.jongin.domain.enumes.BinbangType;
import com.project.jongin.domain.enumes.BuildType;
import com.project.jongin.domain.enumes.PayType;
import com.project.jongin.domain.repository.MemberRepository;
import com.project.jongin.domain.repository.SalesRepository;
import com.project.jongin.securiy.MemberRole;


@SpringBootTest
class JoinJinApplicationTests {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	@Autowired
	SalesRepository salesRepository;
	
	@Test
	void 테스트데이터() {
		IntStream.rangeClosed(1, 3).forEach(i->{
			
			BinbangType salesType;
			salesType=BinbangType.APT;
			
			BuildType salesBuildType;
			salesBuildType=BuildType.DETACH;
			
			PayType salesPayType;
			salesPayType=PayType.MONTH;
			salesRepository.save(SalesEntity.builder()
					.salesAddress("노원구")
					.salesType(salesType)
					.salesBuildType(salesBuildType)
					.salesPayType(salesPayType).build());
		});
	}
	
	
//	@Test
	void 관리자생성() {
		memberRepository.save(MemberEntity.builder()
				.memberEmail("1")
				.memberPass(passEncoder.encode("1"))
				.memberName("관리자")
				.memberIp("127.0.0.225")
				.build().addMemberRole(MemberRole.ADMIN).addMemberRole(MemberRole.USER));
	}
//	@Test
	void 회원생성() {
		memberRepository.save(MemberEntity.builder()
				.memberEmail("user@user.user")
				.memberPass(passEncoder.encode("user"))
				.memberName("유저")
				.memberIp("127.0.0.225")
				.build().addMemberRole(MemberRole.USER));
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
//	@Test
	void 회원가입테스트() {
		memberRepository.save(MemberEntity.builder()
				.memberEmail("3").memberPass("1").memberName("1").memberIp("127.0.0.225")
				.build());
	}
	
	@Rollback(value = false)
	@Transactional
//	@Test
	void 회원수정테스트() {
		memberRepository.findById(2L).orElseThrow().memberName("이름변경").memberPass("2");
	}

}
