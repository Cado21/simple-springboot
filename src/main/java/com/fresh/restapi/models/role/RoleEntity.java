package com.fresh.restapi.models.role;

import com.fresh.restapi.models.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "role_table")
public class RoleEntity extends BaseEntity {
    private static final long serialVersionUID = 172318273L;

    @Column(unique = true, nullable = false)
    private String name;

}
