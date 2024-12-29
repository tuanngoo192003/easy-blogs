package com.project.easyblogs.entities.auth;

import com.project.core.entities.BaseEntity;
import com.project.core.entities.NanoIdsGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "tokens", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Token extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "nanoIds-generator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "nanoIds-generator", type = NanoIdsGenerator.class)
    private String id;

    private String username;
    private String token;
    private LocalDateTime expiredAt;
}
