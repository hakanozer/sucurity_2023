package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotNull
    @NotEmpty
    @Email
    @Length(min=5, max = 15)
    private String email;

    @NotNull
    @NotEmpty
    @Length(min=3, max = 10)
    private String password;

    private String status;

}
