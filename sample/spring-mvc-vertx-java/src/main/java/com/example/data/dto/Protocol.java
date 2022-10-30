package com.example.data.dto;

import com.example.data.enu.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Protocol {

    private Action action;

    private String userId;

    private String content;
}
