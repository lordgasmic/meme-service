package com.lordgasmic.memeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "meme_request_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntity {

    @Id
    private String name;
}
