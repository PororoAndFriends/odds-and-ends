package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // annotation 기반의 경우 문자를 반환하는 것도 가능
    // @RequestMapping에서 method를 정의함으로써 정의하지 않은 방식은 지원하지 않게 할 수 있음
    // 보통 단순 조회의 경우 GET, 저장과 같은 민감한 정보가 있을 경우 POST를 사용
    // 이를 위해 @PostMapping, @GetMapping 어노테이션이 따로 만들어짐
//    @RequestMapping(value = "new-form", method = RequestMethod.GET)
    @GetMapping("new-form")
    public String newForm() {
        return "new-form";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    // @RequestParam을 통해 request의 파라미터 정보를 바로 가져올 수 있고, 타입 캐스팅도 유연하게 가져올 수 있음
    // Model을 파라미터로 받으면 model을 하나 생성하여 가져다줌
    // Get의 쿼리파라미터, Post의 Form 방식을 모두 지원함
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}
