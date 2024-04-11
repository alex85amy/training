package com.example.training.model.po;

import com.example.training.model.config.ChannelTagMappingId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(ChannelTagMappingId.class)
@Table(name = "channel_tag_mapping")
@Getter
@Setter
public class ChannelTagMapping {
    @Id
    @Column(name = "s_area_id")
    private String sAreaId;

    @Id
    @Column(name = "tag_id")
    private Long tagId;


}
