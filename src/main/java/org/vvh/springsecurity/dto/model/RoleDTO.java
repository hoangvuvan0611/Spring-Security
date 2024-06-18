package org.vvh.springsecurity.dto.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    public long id;
    public String roleName;
}
