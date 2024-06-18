package org.vvh.springsecurity.service;

import org.vvh.springsecurity.entity.Role;

import java.util.List;

public interface RoleService {
    void registerRole();
    List<Role> getRoles();
}
