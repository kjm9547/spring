package com.example.semina.service;

import com.example.semina.domain.Member;
import com.example.semina.repogitory.MemberRepository;
import com.example.semina.repogitory.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByname(member.getName())
        .ifPresent(m->{
             throw  new IllegalStateException("이미 존재하는 회원입니다.");
         });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findeone(Long memberId){
        return memberRepository.findById(memberId);
    }
}
