package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    // bulid.gradle에서 JPA라이브러리를 받아오면 spring boot에서 동으로 EntityManager를 데이터베이스와 연동하여 받아옴
    // injection만 해주면 됨
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


    // PK 기반이 아닌 것들은 JPQL을 작성해 줘야함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> resultList = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        return resultList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL. table이 아닌 Entity를 대상으로 쿼리를 날림
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
