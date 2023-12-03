package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.Facets;
import com.lordgasmic.memeservice.entity.TagEntity;
import com.lordgasmic.memeservice.entity.TagEntityPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<TagEntity, TagEntityPK> {

    List<TagEntity> findByPkIdIn(List<String> ids);

    List<TagEntity> findByTag(String tag);

    List<TagEntity> findAll();

    @Query("select distinct tag, count(tag) as count from meme_tag_vw group by tag order by count desc")
    List<Facets> getFacets();
}
