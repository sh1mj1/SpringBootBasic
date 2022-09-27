package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /*
    스프링 컨테이너에서 memberRepository 을 찾는다.
    근데 등록한게 없는데..? 하나 있따! 바로 SpringDataJpaMemberRepository 임.
    SpringDataJpaMemberRepository 에서도 주석으로 서술했듯이
    JpaRepository 을 상속받고 있으면 자동으로 구현해줌!
     */

//    private DataSource dataSource; // 순수 jdbc, jdbc template 이용 시 사용

//    @PersistenceContext // 스펙(?)에서는 이렇게 받아야 하는데 스프링에서는 이렇게만 해줘도 DI 주입됨.

    /*
    private EntityManager em; // JPA

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em){
        this.em = em;
//        this.dataSource = dataSource; // 순수 jdbc, jdbc template 이용 시 사용
    }
    */

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();          // Memory
//        return new JdbcMemberRepository(dataSource);    // 순수 jdbc 이용
//        return new JdbcTemplateMemberRepository(dataSource);    // jdbc template 이용 (실무)
        return new JpaMemberRepository(em);

    }
     */


}
