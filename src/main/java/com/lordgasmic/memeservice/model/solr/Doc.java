package com.lordgasmic.memeservice.model.solr;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

@Data
@AllArgsConstructor
public class Doc {
    @Field
    private String id;
    @Field
    private List<String> tag;
}
