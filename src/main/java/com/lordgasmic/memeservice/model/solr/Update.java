package com.lordgasmic.memeservice.model.solr;

import lombok.Data;

@Data
public class Update {
    private Delete delete;
    private Add add;
    private Commit commit;
}
