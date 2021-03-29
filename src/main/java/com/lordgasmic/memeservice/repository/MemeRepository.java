package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.MemeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemeRepository extends CrudRepository<MemeEntity, String> {

    List<MemeEntity> findAll();

    List<MemeEntity> findByIdIn(List<String> ids);
}
