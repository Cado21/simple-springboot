package com.fresh.restapi.repositories;

import com.fresh.restapi.models.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    //    @Query("FROM ItemEntity as i WHERE i.id=:id")
//    ItemEntity getOne(@Param("id") Integer id);
    Optional<ItemEntity> findById(Long id);
}
