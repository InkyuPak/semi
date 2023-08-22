package com.semi.domain.member;

import com.semi.web.member.MemberSaveDto;
import com.semi.web.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void join(){
        Member member = new Member("inkyu");
        memberRepository.save(member);
        List<Member> result = memberRepository.findAll();

        assertThat(result).extracting("userId").containsExactly("inkyu");
    }

    @Test
    @Commit
    public void join2(){
        MemberSaveDto member1 = new MemberSaveDto("inkyu");
        MemberSaveDto member2 = new MemberSaveDto("kyukyu");
        MemberSaveDto member3 = new MemberSaveDto("kyukyu");
        memberService.joinMember(member1);
        memberService.joinMember(member2);

        List<Member> result = memberRepository.findAll();

        for (Member member : result) {
            System.out.println("member = " + member.getUserId());
        }
    }

}