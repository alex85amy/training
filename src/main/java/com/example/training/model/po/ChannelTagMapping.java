package com.example.training.model.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "channel_tag_mapping")
@Getter
@Setter
public class ChannelTagMapping {
    @Id
    @Column(name = "soure_area_id")
    private String soureAreaId;

    @Id
    @Column(name = "tag_id")
    private int tagId;
}
