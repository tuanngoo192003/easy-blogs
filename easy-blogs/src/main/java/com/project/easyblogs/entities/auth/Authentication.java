package com.project.easyblogs.entities.auth;

import com.project.core.entities.BaseEntity;
import com.project.core.entities.NanoIdsGenerator;
import com.project.core.helper.StringListHelper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Table(name = "authentication", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Authentication extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "nanoIds-generator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "nanoIds-generator", type = NanoIdsGenerator.class)
    private String id;

    private String accountId;

    private String userCaseId;
}
