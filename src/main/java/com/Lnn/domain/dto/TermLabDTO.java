package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TermLabDTO {

    private String term;

    private Integer labId;

    private String labName;

    private String state;

}
