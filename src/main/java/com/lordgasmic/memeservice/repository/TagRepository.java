package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.TagEntity;
import com.lordgasmic.memeservice.entity.TagEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<TagEntity, TagEntityPK> {

    List<TagEntity> findByPkIdIn(List<String> ids);

    List<TagEntity> findByTag(String tag);

    List<TagEntity> findAll();
}
