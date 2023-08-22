package com.semi.web.member;

import com.semi.domain.member.Member;
import com.semi.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long joinMember(MemberSaveDto form) {
        Member member = form.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)
    public String validateDuplicateMember(String userId) {
        String findUserId = memberRepository.findByUserId(userId);

        return findUserId;
    }

    @Transactional(readOnly = true)
    public Page<MemberListDto> listMember(Pageable pageable) {
        Page<Member> result = memberRepository.findAllOrderByCreatedDateDesc(pageable);
        Page<MemberListDto> members = result.map(member -> new MemberListDto(member));
        return members;
    }
}
