package com.example.semina.repogitory;

import com.example.semina.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    public static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);//id세팅
        store.put(member.getId(),member);//map에 데이터 저장
        return member;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByname(String name) {
        return store.values().stream().filter(member->member.getName().equals(name)).findAny();
    }
    public void clearStore(){
        store.clear();
    }
}
