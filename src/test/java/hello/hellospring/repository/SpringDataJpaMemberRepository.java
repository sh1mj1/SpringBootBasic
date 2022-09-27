package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/*
SpringDataJpaMemberRepository 에는 인터페이스ㄱ만 있다.
SpringDataJpaMemberRepository 가 구현체로 JpaRepository 을 상속하고 있으면
자동으로 구현체를 만들고 spring Bean 에 자동으로 등록해준다.
우리는 그걸 그냥 가져다 쓰면 된다. -> SpringConfig 에서 계속
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> find

}
