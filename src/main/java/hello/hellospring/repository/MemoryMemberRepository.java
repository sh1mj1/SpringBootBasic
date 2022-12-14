package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일 때는 Concurrent hashmap 을 써야하긴 함.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 여기서도 실제로는 동시성 문제를 고려해서 해야 함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());

    }

    public void clearStore(){
        store.clear();
    }
}
