package com.example.data.dao;

import com.example.data.po.MessagePo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<MessagePo, Long> {
}
