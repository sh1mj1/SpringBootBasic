package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 리포지토리같은 경우 서비스보다는 더 기계적인 느낌으로 메서드를 네이밍한다.
 * 반면 서비스는 비즈니스에 의존적으로 설계를 한다. (메서드 이름을)
 */

@Transactional
public class MemberService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 이 클래스 내에서 객체를 생성하는 게 아니라 외부에서 넣어주도록 constructor 을 만들어서 사용.

    /**
     * Sign in
     *
     * @param member 회원
     * @return memberId
     */
    public Long join(Member member) {
        // 예시 규칙 - 같은 이름의 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        /*
        long start = System.currentTimeMillis();
        try {
            validateDuplicatedMember(member); // 중복회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

         */

        validateDuplicatedMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * find All Members
     *
     * @return members
     */
    public List<Member> findMembers() {
        /*
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }

         */
         return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
