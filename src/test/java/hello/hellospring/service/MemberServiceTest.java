package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void signIn() {
        // given - BDD 패턴. given, when, then
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicatedMemberException(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when

        // 아래처럼 할 수 있음. 하지만 테스트시 try catch 를 넣는게 애매하다..
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail(); // 예외가 발생해야 하는데 발생하지 않음.
//        } catch (IllegalStateException e) {
//            // 예외가 정상적으로 발생.
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1");
//        }

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then



    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}