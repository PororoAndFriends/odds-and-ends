package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    // Autowired : 스프링 컨테이너에서 MemberService를 가져와 매칭해줌
    // 따라서 MemberService를 찾아야 하기 때문에 @Service를 class에 걸어줘야 함
    // 마찬가지로 Repository에도 @Repository를 걸어줘야 함
    // 혹은 SpringConfig에 설정하여 직접 컨테이너에 추가할 수도 있음
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // MemberForm 클래스를 매개변수로 넣어주면 Spring에서 자동으로 객체를 생성하여 사용
    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){

        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);

        return "members/memberList";
    }

}
