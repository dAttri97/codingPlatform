package com.phonepe.interview.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class User {
    private long id;
    private String name;
    private String email;
    private String info;
}
