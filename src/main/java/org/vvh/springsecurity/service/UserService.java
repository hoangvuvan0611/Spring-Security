package org.vvh.springsecurity.service;

import org.vvh.springsecurity.dto.model.UserDTO;
import org.vvh.springsecurity.dto.request.SignUpRequest;

import java.util.List;

public interface UserService {
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
}
