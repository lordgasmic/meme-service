package com.lordgasmic.memeservice.model.solr;

import com.lordgasmic.memeservice.entity.TagEntity;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class Doc {
    @Field
    private String id;
    @Field
    private String tag;

    public static Doc fromTagEntity(TagEntity entity) {
        Doc doc = new Doc();
        doc.setId(entity.getPk().getId());
        doc.setTag(entity.getTag());
        return doc;
    }
}
