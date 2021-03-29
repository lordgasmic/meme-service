package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.TagEntity;
import com.lordgasmic.memeservice.entity.TagEntityPK;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity, TagEntityPK> {
}
