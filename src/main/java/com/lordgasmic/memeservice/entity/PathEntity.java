package com.lordgasmic.memeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "meme_path_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathEntity {

    @Id
    private String id;
    private String path;
    @Column(name = "thumbnail_path")
    private String thumbnailPath;
}
