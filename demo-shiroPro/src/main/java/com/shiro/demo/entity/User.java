package com.shiro.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements Serializable {

    @Id
    private long id;

    private String username;

    private String password;

    private int admin;

    @ManyToMany
    @JoinTable(name = "user_role_rel",joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "roleId"),foreignKey = @ForeignKey(name = "null"))
    private List<Role> roles;
}
