package com.example.restfulservice.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class HelloWorldBean {

    private String message;

    private HelloWorldBean(String message) {
        this.message = message;
    }

    public static HelloWorldBean from(String message) {
        return new HelloWorldBean(message);
    }
}
