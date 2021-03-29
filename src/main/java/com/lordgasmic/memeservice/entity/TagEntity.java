package com.lordgasmic.memeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "meme_tag_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {

    @EmbeddedId
    private TagEntityPK id;
    private String tag;
}
