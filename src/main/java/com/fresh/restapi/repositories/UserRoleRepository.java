package com.fresh.restapi.repositories;


import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.models.user.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findAllByUser(UserEntity user);
}
