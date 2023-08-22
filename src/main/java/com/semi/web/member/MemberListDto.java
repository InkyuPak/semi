package com.semi.web.member;

import com.semi.domain.member.Gender;
import com.semi.domain.member.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberListDto {

    private String userId;
    private String username;
    private Gender gender;
    private String email;
    private LocalDateTime createdDate;

    public MemberListDto(Member member) {
        this.userId = member.getUserId();
        this.username = member.getUsername();
        this.gender = member.getGender();
        this.email = member.getEmail();
        this.createdDate = member.getCreatedDate();
    }
}
