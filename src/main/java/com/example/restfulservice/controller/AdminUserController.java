package com.example.restfulservice.controller;

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

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/users")
public class AdminUserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public MappingJacksonValue findById4Admin(@PathVariable("id") Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        AdminUserResponseDto adminUserResponseDto = new AdminUserResponseDto();

        // userResponseDto의 정보를 adminUserResponseDto에 복사
        BeanUtils.copyProperties(userResponseDto, adminUserResponseDto);

        // admin에게 보여줄 정보를 필터링
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        // 필터링한 결과를 JSON 형식으로 매핑
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUserResponseDto);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
