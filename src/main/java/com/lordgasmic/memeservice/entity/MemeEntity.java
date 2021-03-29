package com.lordgasmic.memeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "meme_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeEntity {

    @Id
    private String id;
}
