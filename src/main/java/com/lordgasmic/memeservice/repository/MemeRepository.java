package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.MemeEntity;
import org.springframework.data.repository.CrudRepository;

public interface MemeRepository extends CrudRepository<MemeEntity, String> {
}
