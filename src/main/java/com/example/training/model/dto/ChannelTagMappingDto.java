package com.example.training.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@Getter
@Setter
public class ChannelTagMappingDto {
    private String sAreaId;
    private Long tagId;

}
