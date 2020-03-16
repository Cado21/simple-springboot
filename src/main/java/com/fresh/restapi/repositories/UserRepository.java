package com.fresh.restapi.repositories;

import com.fresh.restapi.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    void deleteByUsername(String username);

    List<UserEntity> findAllByUsername(String username);

    @Query("SELECT r.name FROM UserEntity u JOIN UserRoleEntity ur ON u.id = ur.id JOIN RoleEntity r on ur.id = r.id WHERE u.username=:username")
    List<String> getRolesByUsername(String username);


}
