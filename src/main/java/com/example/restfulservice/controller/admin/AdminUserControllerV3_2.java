package com.example.restfulservice.controller.admin;

import com.example.restfulservice.service.UserService;
import com.example.restfulservice.service.dto.AdminUserResponseDto;
import com.example.restfulservice.service.dto.UserResponseDto;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 헤더 값을 통한 버전 관리 (일반 브라우저에서 실행 불가)
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/users")
public class AdminUserControllerV3_2 {

    private final UserService userService;

    @GetMapping(headers = "X-API-VERSION=3")
    public MappingJacksonValue findAll4Admin() {
        List<AdminUserResponseDto> adminUserResponseDtoResults = userService.findAll().stream()
                .map(userResponseDto -> {
                    AdminUserResponseDto adminUserResponseDto = new AdminUserResponseDto();
                    BeanUtils.copyProperties(userResponseDto, adminUserResponseDto);
                    return adminUserResponseDto;
                })
                .toList();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUserResponseDtoResults);
        applyJacksonFilter(mappingJacksonValue);

        return mappingJacksonValue;
    }

    @GetMapping(value = "/{id}", headers = "X-API-VERSION=3")
    public MappingJacksonValue findById4Admin(@PathVariable("id") Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        AdminUserResponseDto adminUserResponseDto = new AdminUserResponseDto();

        BeanUtils.copyProperties(userResponseDto, adminUserResponseDto);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUserResponseDto);
        applyJacksonFilter(mappingJacksonValue);

        return mappingJacksonValue;
    }

    private void applyJacksonFilter(MappingJacksonValue mappingJacksonValue) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        mappingJacksonValue.setFilters(filters);
    }
}
