package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    // AfterEach : 하나의 Test 종료 후 자동 실행
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();

        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // Assertion : JUnit에서 지원하는 값 확인법. 앞에 기대하는 값, 뒤에 실제 값
        // 실행했을 때 참일 경우 결과창에 초록 체크표시
//        Assertions.assertEquals(member, result);

        // 요즘 많이 쓰는 방식
        // org.assertj.core.api.Assertions
        // import static을 이용해 많이 사용
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
