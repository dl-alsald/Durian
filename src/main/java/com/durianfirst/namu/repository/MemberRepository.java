package com.durianfirst.namu.repository;

import com.durianfirst.namu.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
