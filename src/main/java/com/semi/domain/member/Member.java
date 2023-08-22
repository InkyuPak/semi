package com.semi.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    private String email;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String phoneNumber;
    @Embedded
    private Address address;

    public Member(String userId) {
        this.userId = userId;
    }

    public Member(String userId, String password, String username, Gender gender, String email, String tel, String phoneNumber, Address address) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.tel = tel;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
