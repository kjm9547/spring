package com.example.semina.service;

import com.example.semina.domain.Member;

import com.example.semina.repogitory.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMemeber= memberService.findeone(saveId).get();
        assertThat(member.getName()).isEqualTo(findMemeber.getName());

    }
    @Test
    public void dupli(){
        //given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        memberService.join(member1);
        //memberService.join(member2);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findeone() {
    }
}