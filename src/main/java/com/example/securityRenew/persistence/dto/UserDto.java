package com.example.securityRenew.persistence.dto;

import com.example.securityRenew.persistence.model.Role;
import com.example.securityRenew.persistence.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;

    private String password;

    private String name;

    private Set<Long> roles;

    public User toEntity(Set<Role> roles) {
        return User.builder().id(id).password(password).name(name).roles(roles).build();
    }
}