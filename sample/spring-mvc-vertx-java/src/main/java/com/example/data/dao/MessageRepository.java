package com.example.data.dao;

import com.example.data.po.MessagePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageRepository extends JpaRepository<MessagePo, Long>, JpaSpecificationExecutor<MessagePo> {
}
