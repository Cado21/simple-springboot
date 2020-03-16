package com.fresh.restapi.converters;

import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.models.item.ItemEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemConverter extends BaseEntityConverter<ItemEntity, ItemResponseDTO>{
    public ItemResponseDTO toResponseDTO (ItemEntity entity ) {
        ItemResponseDTO responseDTO = new ItemResponseDTO();
        BeanUtils.copyProperties(entity, responseDTO);
        this.setBaseEntityDTO( entity, responseDTO );
        return responseDTO;
    }
    public List<ItemResponseDTO> toListResponseDTO( List<ItemEntity> entities ) {
        List<ItemResponseDTO> responseDTO = new ArrayList<>();
        entities.forEach(entity -> {
            ItemResponseDTO dto = new ItemResponseDTO();
            BeanUtils.copyProperties(entity, dto);
            this.setBaseEntityDTO( entity, dto );
            responseDTO.add(dto);
        });
        return responseDTO;
    }
}
