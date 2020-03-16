package com.fresh.restapi.models.user;

import com.fresh.restapi.models.BaseEntity;
import com.fresh.restapi.models.role.RoleEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "user_role_table")
public class UserRoleEntity extends BaseEntity {
    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "role_id")
    @ManyToOne
    private RoleEntity role;
}
