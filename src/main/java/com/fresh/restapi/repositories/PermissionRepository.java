package com.fresh.restapi.repositories;

import com.fresh.restapi.models.permission.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    List<PermissionEntity> findAll();

    Optional<PermissionEntity> findByHttpMethodAndPathUri(String httpMethod, String pathUri);

}