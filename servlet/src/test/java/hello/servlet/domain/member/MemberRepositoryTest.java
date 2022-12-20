package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

public class MemberRepositoryTest {
  
  MemberRepository memberRepository = MemberRepository.getInstance();
  
  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }
  
  @Test
  public void save() throws Exception {
    //given
    Member member = new Member("hello", 20);
  
    //when
    Member saveMember = memberRepository.save(member);
    
    //then
    Member findMember = memberRepository.findById(saveMember.getId());
    assertThat(findMember).isEqualTo(saveMember);
  }
  
  @Test
  public void findAll() throws Exception {
    //given
    Member member1 = new Member("member1", 20);
    Member member2 = new Member("member2", 30);
  
    memberRepository.save(member1);
    memberRepository.save(member2);
    
    //when
    List<Member> result = memberRepository.findAll();
    
    //then
    assertThat(result.size()).isEqualTo(2);
    assertThat(result).contains(member1, member2);
  }
  
}