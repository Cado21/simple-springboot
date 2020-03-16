package com.fresh.restapi.repositories;

import com.fresh.restapi.models.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findAll();

    Optional<RoleEntity> findByName(String name);

    Optional<RoleEntity> findById(Integer id);

}
