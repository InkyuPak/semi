package com.semi.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @GetMapping("/idDuplicateProc/{userId}")
    public String idDuplicateProc(@PathVariable String userId){
        return memberService.validateDuplicateMember(userId);
    }
}
