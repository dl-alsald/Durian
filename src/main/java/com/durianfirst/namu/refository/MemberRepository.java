package com.durianfirst.namu.refository;

import com.durianfirst.namu.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {


    @Query("select m from Member m where m.mid = :mid and m.msocial = false ")
    Optional<Member> getWithRoles(String mid);
}
