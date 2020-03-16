package com.fresh.restapi.converters;

import com.fresh.restapi.models.BaseEntity;
import com.fresh.restapi.dtos.BaseEntityResponseDTO;

import java.time.ZonedDateTime;

public abstract class BaseEntityConverter <E extends BaseEntity, D extends BaseEntityResponseDTO>{

    protected void setBaseEntityDTO(E entity, D dto) {
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(ZonedDateTime.now());
    }

}
