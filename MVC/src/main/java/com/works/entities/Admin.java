package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @NotNull
    @NotEmpty
    @Email
    @Length(min=5, max = 15)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private StatusType status;

}
