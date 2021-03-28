package com.lordgasmic.==PACKAGE_NAME==.repository;

import com.lordgasmic.==PACKAGE_NAME==.entity.UserEntity;
import org.springframework.data.repository.Repository;

public interface LoginRepository extends Repository<UserEntity, String> {

    void save(UserEntity entity);
}
