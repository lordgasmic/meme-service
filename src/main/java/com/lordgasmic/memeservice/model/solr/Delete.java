package com.lordgasmic.memeservice.model.solr;

import lombok.Getter;

@Getter
public class Delete {
    private String query;

    public Delete() {
        query = "*:*";
    }
}
