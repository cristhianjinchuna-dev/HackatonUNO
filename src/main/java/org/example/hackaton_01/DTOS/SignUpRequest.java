package org.example.hackaton_01.DTOS;

import lombok.Getter;
import lombok.Setter;
import org.example.hackaton_01.account.domain.Role;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
