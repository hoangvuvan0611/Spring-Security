package org.vvh.springsecurity.dto.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public String username;
    public List<RoleDTO> roles;
}
