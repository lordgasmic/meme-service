package com.lordgasmic.memeservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "meme_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeEntity {

    @Id
    private String id;
}
