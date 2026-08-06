package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}