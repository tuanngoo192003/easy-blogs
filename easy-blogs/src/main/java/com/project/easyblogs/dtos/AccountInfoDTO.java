package com.project.easyblogs.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoDTO {
    private String id;

    private String username;

    private String email;

    private String password;

    private String status;

    private boolean verified;

    private String roles;
}
