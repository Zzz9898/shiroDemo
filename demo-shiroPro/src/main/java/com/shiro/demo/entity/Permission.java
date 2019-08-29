package com.shiro.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Permission implements Serializable {

    @Id
    private long id;

    private String name;

    private String presource;
}
