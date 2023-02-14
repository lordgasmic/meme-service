package com.lordgasmic.memeservice.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "meme_tag_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {

    @EmbeddedId
    private TagEntityPK pk;
    private String tag;
}
