package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// @controller 어노테이션이 있으면 멤버 컨트롤러 객체를 생성해서 스프링 container 에 넣어둠.

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        // AOP 기술을 사용한 후 프록시가 붙는지 확인하기
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * 회원을 실제 등록하는 기능
     * @param form
     * @return "redirect:/"
     */
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }

}
