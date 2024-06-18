package org.vvh.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vvh.springsecurity.dto.response.ResponseData;
import org.vvh.springsecurity.dto.response.ResponseDataList;
import org.vvh.springsecurity.dto.response.ResponseError;
import org.vvh.springsecurity.entity.Role;
import org.vvh.springsecurity.service.RoleService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseData<?> createRole() {
        log.info("Creating role");
        try {
            roleService.registerRole();
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create role successfully", null);
        } catch (Exception e) {
            log.info("Creating error {}: ", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Create role error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseData<?> getAllRole() {
        log.info("Get all role");
        try {
            ResponseDataList<Role> roleResponseDataList = new ResponseDataList<>(roleService.getRoles());
            return new ResponseData<>(HttpStatus.OK.value(), "Get all role successfully", roleResponseDataList);
        } catch (Exception e) {
            log.info("Get all {}: ", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get all role error: " + e.getMessage());
        }
    }
}
