package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    // PK 기반 것들 조회는 em 으로 바로 조회 가능
    // PK 기반이 아닌 것들(대체키)은 JPQL 이라는 쿼리 언어로 쿼리 작성해야 함.
    // 그 다음번에 배울 것은 JPA 을 spring 으로 감싸서 사용할 수 있다. 그렇다면 PK 가 아닌 것들(대체키) 마저도 JPQL 도 안써도 된다.

    private final EntityManager em; // JPA 는 EntityManager 로 모든 것이 동작함.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // JPQL 이라는 쿼리 언어
        // SQL 에서는 우리는 테이브을 대상으로 Query 을 날림.
        // Entity(객체)를 대상으로 Query 을 날리는 것. 그러면 SQL 로 번역이 된다.
        // "select m from Member as m" 와 같음. as 는 alias
        // SQL 에서는 조회 대상이 m.id 나 m.name 혹은 * 인데 여기서는 그냥 객체를 조회!!!
    }
}

