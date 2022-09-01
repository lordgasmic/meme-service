package com.lordgasmic.memeservice.model;

import lombok.Data;

import java.util.List;

@Data
public class MemeResponse {
    private String name;
    private String url;
    private String thumbnailUrl;
    private List<String> tags;
}
