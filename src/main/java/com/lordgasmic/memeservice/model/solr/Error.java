package com.lordgasmic.memeservice.model.solr;

import lombok.Data;

import java.util.List;

@Data
public class Error {
    private List<String> metadata;
    private String msg;
    private int code;
}
