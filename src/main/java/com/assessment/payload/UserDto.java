package com.assessment.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {
    private Long id;
    private String name;
    private int age;
    private String city;
    private String email;
}
