package com.lordgasmic.memeservice.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MemeResponse {
    private String name;
    private String url;
    private List<String> tags;
}
