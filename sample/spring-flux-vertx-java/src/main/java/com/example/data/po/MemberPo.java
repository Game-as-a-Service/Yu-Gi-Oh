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
@Table(value = "member")
public class MemberPo {

    @Id
    private Long id;

    private String userId;

    private String passwordHash;

    private Long createDate;
}
