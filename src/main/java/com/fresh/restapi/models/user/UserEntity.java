package com.fresh.restapi.models.user;

import com.fresh.restapi.models.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "user_table")
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 1234187237648L;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRoles;

}
