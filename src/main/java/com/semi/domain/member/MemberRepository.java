package com.semi.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select count(m) from Member m where m.userId=:userId")
    String findByUserId(@Param("userId") String userId);
    //select * from member userid=:userId

    @Query("select m from Member m")
    Page<Member> findAllOrderByCreatedDateDesc(Pageable pageable);
}
