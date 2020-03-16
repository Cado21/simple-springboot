package com.fresh.restapi;

import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.models.item.ItemEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class MockItemFactory {
    public static List<ItemResponseDTO> getAllItemsReponseDTO() {
        return Collections.singletonList(
                ItemResponseDTO.builder()
                        .name(TestConstants.NAME)
                        .description(TestConstants.DESC_1)
                        .quantity(1)
                        .thumbnail(TestConstants.URL)
                        .build()
        );
    }
    public static List<ItemEntity> getAllItemsEntity() {
        return Collections.singletonList(
                ItemEntity
                        .builder()
                        .name(TestConstants.NAME)
                        .description(TestConstants.DESC_1)
                        .quantity(1)
                        .thumbnail(TestConstants.URL)
                        .build()
        );
    }
}
