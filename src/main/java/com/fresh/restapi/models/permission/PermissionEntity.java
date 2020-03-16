package com.fresh.restapi.models.permission;

import com.fresh.restapi.models.BaseEntity;
import com.fresh.restapi.models.role.RolePermissionEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "permission_table")
public class PermissionEntity extends BaseEntity {

    private static final long serialVersionUID = 3123131231L;

    private String httpMethod;
    private String pathUri;

    @OneToMany(mappedBy = "permission")
    private List<RolePermissionEntity> rolePermissions;

}
