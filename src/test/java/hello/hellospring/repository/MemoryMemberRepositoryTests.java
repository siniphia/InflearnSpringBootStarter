package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTests {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();  // 테스트 하나 끝날때마다 repo 초기화 해야 밑에 애들끼리 안겹침
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");  // 테스트 케이스 목록 작성
        repository.save(member);  // 저장해봄
        Member result = repository.findById(member.getId()).get();  // 저장된거 꺼내기
        Assertions.assertThat(member).isEqualTo(result);  // 같으면 초록색, 틀리면 붉은색
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
