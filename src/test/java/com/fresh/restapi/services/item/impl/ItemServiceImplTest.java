package com.fresh.restapi.services.item.impl;

import com.fresh.restapi.MockItemFactory;
import com.fresh.restapi.converters.ItemConverter;
import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.repositories.ItemRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemServiceImplTest {

    @InjectMocks
    ItemServiceImpl itemService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    ItemConverter itemConverter;


    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(itemRepository, itemConverter);
    }

    @Test
    void getAllItems() {
        when(itemRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(MockItemFactory.getAllItemsEntity());
        when(itemConverter.toListResponseDTO(MockItemFactory.getAllItemsEntity())).thenReturn(MockItemFactory.getAllItemsReponseDTO());

        List<ItemResponseDTO> expected = MockItemFactory.getAllItemsReponseDTO();
        List<ItemResponseDTO> actual = itemService.getAllItems();
        Assert.assertEquals(expected, actual);

        verify(itemRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
        verify(itemConverter, times(1)).toListResponseDTO(MockItemFactory.getAllItemsEntity());

    }

    @Test
    void getItemById() {
    }


}
