package com.example.semina.repogitory;

import com.example.semina.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByname(String name);
    List<Member> findAll();

}
