package com.project.easyblogs.entities.auth;

import com.project.core.entities.BaseEntity;
import com.project.core.entities.NanoIdsGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Table(name = "user_cases", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class UserCase extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "nanoIds-generator", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "nanoIds-generator", type = NanoIdsGenerator.class)
    private String id;

    private String name;

    private String description;
}
