package com.example.data.dao;

import com.example.data.po.MemberPo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface MemberRepository extends ReactiveCrudRepository<MemberPo, Long> {

    Mono<MemberPo> findByUserId(String userId);
}
