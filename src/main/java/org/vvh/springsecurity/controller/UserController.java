package org.vvh.springsecurity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vvh.springsecurity.dto.model.UserDTO;
import org.vvh.springsecurity.dto.response.ResponseData;
import org.vvh.springsecurity.dto.response.ResponseDataList;
import org.vvh.springsecurity.dto.response.ResponseError;
import org.vvh.springsecurity.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseData<?> getUser(@Valid @PathVariable String username) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Getting user " + username, userService.findByUsername(username));
        } catch (Exception e) {
            log.error("Error while getting user ", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Error while getting user " + username);
        }
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasAnyAuthority('USER')")
    public ResponseData<?> getAllUsers() {
        try {
            ResponseDataList<UserDTO> userDTOResponseDataList = new ResponseDataList<>(userService.findAll());
            return new ResponseData<>(HttpStatus.OK.value(), "Getting all users", userDTOResponseDataList);
        } catch (Exception e) {
            log.error("Error while getting users ", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Error while getting users");
        }
    }
}
