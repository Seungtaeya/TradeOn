package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
