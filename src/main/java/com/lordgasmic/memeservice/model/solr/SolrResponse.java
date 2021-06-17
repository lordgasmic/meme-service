package com.lordgasmic.memeservice.model.solr;

import lombok.Data;

@Data
public class SolrResponse {
    private ResposneHeader responseHeader;
    private Error error;
}
