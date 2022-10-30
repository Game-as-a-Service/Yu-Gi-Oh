package com.example.data.dao;

import com.example.data.po.MemberPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberPo, Long>, JpaSpecificationExecutor<MemberPo> {

    Optional<MemberPo> findByUserId(String userId);
}
