package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 extends하면 interface가 구현체를 자동으로 만들어주어 Spring Bean에 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ? 식의 쿼리를 짜줌
    // ? 안에는 findBy 다음에 오는 글자가 들어감
    // findByNameAndId 처럼 And 쿼리도 지원
    @Override
    Optional<Member> findByName(String name);
}
