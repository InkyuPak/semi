package com.semi.web.member;

import com.semi.domain.member.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String join(Model model){
        model.addAttribute("form", new MemberSaveDto());
        return "/members/createMemberForm";
    }


    @PostMapping("/new")
    public String join(@Validated @ModelAttribute("form") MemberSaveDto form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "members/createMemberForm";
        }

        memberService.joinMember(form);
        return "redirect:/";
    }

    @GetMapping
    public String memberList(Pageable pageable, Model model){
        Page<MemberListDto> members = memberService.listMember(pageable);
        model.addAttribute("members", members);

        // 현재 페이지 번호
        model.addAttribute("currentPage", members.getPageable().getPageNumber());

        // 총 페이지 수
        model.addAttribute("totalPages", members.getTotalPages());

        return "members/memberList";
    }

    @ModelAttribute("gender")
    public Gender[] gender(){
        return Gender.values();
    }

    @ModelAttribute("tel")
    public List<Tel> tel(){
        List<Tel> tel = new ArrayList<>();
        tel.add(new Tel("SKT", "SKT"));
        tel.add(new Tel("KT", "KT"));
        tel.add(new Tel("LGT", "LGT"));
        return tel;
    }
}
