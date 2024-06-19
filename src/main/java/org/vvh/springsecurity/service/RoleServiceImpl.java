package org.vvh.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vvh.springsecurity.entity.Role;
import org.vvh.springsecurity.enums.RoleEnum;
import org.vvh.springsecurity.exception.AlreadyExistsException;
import org.vvh.springsecurity.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public void registerRole() {
        if(roleRepository.count() > 0) {
            throw new AlreadyExistsException("Role already exists");
        }
        Role role = Role.builder()
                .name(RoleEnum.USER)
                .build();
        roleRepository.save(role);
        role = Role.builder()
                .name(RoleEnum.ADMIN)
                .build();
        roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
