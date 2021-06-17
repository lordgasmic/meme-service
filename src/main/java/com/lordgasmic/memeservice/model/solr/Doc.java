package com.lordgasmic.memeservice.model.solr;

import com.lordgasmic.memeservice.entity.TagEntity;
import lombok.Data;

@Data
public class Doc {
    private String id;
    private String tag;

    public static Doc fromTagEntity(TagEntity entity) {
        Doc doc = new Doc();
        doc.setId(entity.getPk().getId());
        doc.setTag(entity.getTag());
        return doc;
    }
}
