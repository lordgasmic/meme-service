package com.lordgasmic.==PACKAGE_NAME==.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "_vw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String username;
    private String password;
    private String salt;
    private boolean enabled;
}
