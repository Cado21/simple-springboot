package com.fresh.restapi.repositories;

import com.fresh.restapi.models.role.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
    List<RolePermissionEntity> findAllByRole_Name(String name);

    void deleteAllByRoleId(Long roleId);
}
