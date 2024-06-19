package org.vvh.springsecurity.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vvh.springsecurity.entity.Role;
import org.vvh.springsecurity.enums.RoleEnum;
import org.vvh.springsecurity.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class SetupData implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        //Create Role
        Role roleUser = Role.builder()
                .name(RoleEnum.USER)
                .build();
        roleRepository.save(roleUser);
        Role roleAdmin = Role.builder()
                .name(RoleEnum.ADMIN)
                .build();
        roleRepository.save(roleAdmin);
    }
}
