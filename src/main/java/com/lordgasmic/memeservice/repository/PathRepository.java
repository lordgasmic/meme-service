package com.lordgasmic.memeservice.repository;

import com.lordgasmic.memeservice.entity.PathEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PathRepository extends CrudRepository<PathEntity, String> {

    List<PathEntity> findAllById(List<String> ids);
}
