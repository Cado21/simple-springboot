package com.fresh.restapi.models.role;

import com.fresh.restapi.models.BaseEntity;
import com.fresh.restapi.models.permission.PermissionEntity;
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
@Table(name = "role_permission_table")
public class RolePermissionEntity extends BaseEntity {

    @JoinColumn
    @ManyToOne
    private PermissionEntity permission;

    @JoinColumn
    @ManyToOne
    private RoleEntity role;
}
