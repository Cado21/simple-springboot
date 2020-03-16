package com.fresh.restapi;

import com.fresh.restapi.dtos.requests.item.ItemRequestDTO;
import com.fresh.restapi.dtos.requests.item.ItemUpdateRequestDTO;
import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.models.item.ItemEntity;

import java.util.Collections;
import java.util.List;

public class MockItemFactory {
    public static List<ItemResponseDTO> getAllItemsReponseDTO() {
        return Collections.singletonList(getItemResponseDTO());
    }

    public static List<ItemEntity> getAllItemsEntity() {
        return Collections.singletonList(getItemEntity());
    }

    public static ItemEntity getItemEntity() {
        return ItemEntity
                .builder()
                .name(TestConstants.NAME)
                .description(TestConstants.DESC_1)
                .quantity(1)
                .thumbnail(TestConstants.URL)
                .build();
    }
    public static ItemResponseDTO getItemResponseDTO () {
        return ItemResponseDTO.builder()
                .name(TestConstants.NAME)
                .description(TestConstants.DESC_1)
                .quantity(1)
                .thumbnail(TestConstants.URL)
                .build();
    }
    public static ItemRequestDTO getItemRequestDTO() {
        return ItemRequestDTO.builder()
                .name(TestConstants.NAME)
                .description(TestConstants.DESC_1)
                .quantity(1)
                .thumbnail(TestConstants.URL)
                .build();
    }
}
