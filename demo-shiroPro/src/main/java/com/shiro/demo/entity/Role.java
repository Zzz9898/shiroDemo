package com.shiro.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Role implements Serializable {

    @Id
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission_rel",joinColumns = @JoinColumn(name = "roleId"),inverseJoinColumns = @JoinColumn(name = "permissionId"),foreignKey = @ForeignKey(name = "null"))
    private List<Permission> permissions;
}
