package com.sk.jdp.common.sample.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sk.jdp.common.core.model.BaseObject;

import lombok.Data;


@Data
public class User extends BaseObject {
   
    private int id;

    @NotBlank(message="name null")
    private String name;

    @NotBlank
    @Email(message="email null")
    private String email;

    @Min(1)
    @NotNull(message="age null")
    private int age;
}
