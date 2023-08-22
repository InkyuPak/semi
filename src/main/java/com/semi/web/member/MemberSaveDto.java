package com.semi.web.member;

import com.semi.domain.member.Address;
import com.semi.domain.member.Gender;
import com.semi.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberSaveDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "사용자 ID는 4자 이상 20자 이하로 입력해주세요.")
    private String userId;

    @NotBlank
    @Size(min = 2, max = 16, message = "비밀번호는 최소 2자 이상 입력해주세요.")
    private String password;

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "성별을 선택해주세요.")
    private String gender;

    @Email
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "통신사를 선택해주세요.")
    private String tel;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    private Address address;

    public void setAddress(String zipcode, String city, String street) {
        this.address = new Address(zipcode, city, street);
    }
    public MemberSaveDto(String userId) {
        this.userId = userId;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .password(password)
                .username(username)
                .gender(Gender.valueOf(gender))
                .email(email)
                .tel(tel)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();

    }
}
