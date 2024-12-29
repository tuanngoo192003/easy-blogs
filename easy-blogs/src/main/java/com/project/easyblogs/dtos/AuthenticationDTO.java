package com.project.easyblogs.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    private String id;

    private String accountId;

    private String userCaseId;

    private String roles;
}
