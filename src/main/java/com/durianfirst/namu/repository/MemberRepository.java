package com.durianfirst.namu.repository;

import com.durianfirst.namu.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.msocial = false ")
    Optional<Member> getWithRoles(String mid);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByMemail(String memail);

    @Modifying
    @Transactional
    @Query("update Member m set m.mpw =:mpw where m.mid = :mid")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);

    /** Security - username이 DB에 존재하는지 확인 **/
    @EntityGraph(attributePaths = "roleSet")
    Member findByMid(String mid);


}
