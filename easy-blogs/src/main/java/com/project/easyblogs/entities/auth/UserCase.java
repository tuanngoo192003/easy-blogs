package com.project.easyblogs.entities.auth;

import com.project.core.entities.BaseEntity;
import com.project.core.entities.NanoIdsGenerator;
import com.project.core.helper.StringListHelper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@Table(name = "usercases", schema = "auth")
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

    @Convert(converter = StringListHelper.class)
    private List<String> roles;
}
