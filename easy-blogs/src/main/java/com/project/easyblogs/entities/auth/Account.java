package com.project.easyblogs.entities.auth;

import com.project.core.entities.BaseEntity;
import com.project.core.entities.NanoIdsGenerator;
import com.project.core.enums.AccountStatus;
import com.project.core.helper.StringListHelper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@Table(name = "accounts", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Account extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "nanoIds-generator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "nanoIds-generator", type = NanoIdsGenerator.class)
    private String id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private boolean isVerified;

    @Convert(converter = StringListHelper.class)
    private List<String> roles;
}
