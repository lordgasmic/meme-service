package com.lordgasmic.memeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacetsResponse {
    private String tag;
    private long count;
}
