package com.example.data.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "message")
public class MessagePo {

    @Id
    private Long id;

    private String userId;

    private String action;

    private String content;

    private Long createDate;
}
