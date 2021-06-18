package com.lordgasmic.memeservice.model.solr;

import lombok.Data;

import java.util.List;

@Data
public class Update {
    private Delete delete;
    private List<Doc> add;
    private Commit commit;
}
