package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.RequestEntity;
import com.lordgasmic.memeservice.entity.TagEntity;
import com.lordgasmic.memeservice.entity.TagEntityPK;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<RequestEntity, String> {
}
