package com.lordgasmic.memeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "meme_path_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathEntity {

    @Id
    private String id;
    private String path;
    private String thumbnailPath;
}
