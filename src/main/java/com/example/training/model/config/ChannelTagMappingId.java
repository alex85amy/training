package com.example.training.model.config;

import lombok.Data;

import java.io.Serializable;
@Data
public class ChannelTagMappingId implements Serializable {
    private String sAreaId;
    private Long tagId;

}

