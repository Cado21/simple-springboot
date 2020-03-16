package com.fresh.restapi.services.item.impl;

import com.fresh.restapi.MockGeneralFactory;
import com.fresh.restapi.MockItemFactory;
import com.fresh.restapi.TestConstants;
import com.fresh.restapi.converters.ItemConverter;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.models.item.ItemEntity;
import com.fresh.restapi.repositories.ItemRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(MockItemFactory.getItemEntity()));
        when(itemConverter.toResponseDTO(any(ItemEntity.class))).thenReturn(MockItemFactory.getItemResponseDTO());
        ItemResponseDTO expected = MockItemFactory.getItemResponseDTO();
        ItemResponseDTO actual = itemService.getItemById(anyLong());
        Assert.assertEquals(expected, actual);

        verify(itemRepository, times(1)).findById(anyLong());
        verify(itemConverter, times(1)).toResponseDTO(any(ItemEntity.class));
    }

    @Test
    void createNewItem() {
        when(itemRepository.save(any(ItemEntity.class))).thenReturn(MockItemFactory.getItemEntity());
        when(itemConverter.toResponseDTO(any(ItemEntity.class))).thenReturn(MockItemFactory.getItemResponseDTO());

        ItemResponseDTO expected = MockItemFactory.getItemResponseDTO();
        ItemResponseDTO actual = itemService.createNewItem(MockItemFactory.getItemRequestDTO());
        Assert.assertEquals(expected, actual);

        verify(itemRepository, times(1)).save(any(ItemEntity.class));
        verify(itemConverter, times(1)).toResponseDTO(any(ItemEntity.class));
    }

    @Test
    void updateItemById() {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(MockItemFactory.getItemEntity()));
        when(itemRepository.save(any(ItemEntity.class))).thenReturn(MockItemFactory.getItemEntity());
        when(itemConverter.toResponseDTO(any(ItemEntity.class))).thenReturn(MockItemFactory.getItemResponseDTO());
        ItemResponseDTO expected = MockItemFactory.getItemResponseDTO();
        ItemResponseDTO actual = itemService.updateItemById(anyLong(), MockItemFactory.getItemRequestDTO());
        Assert.assertEquals(expected, actual);

        verify(itemRepository, times(1)).findById(anyLong());
        verify(itemRepository, times(1)).save(any(ItemEntity.class));
        verify(itemConverter, times(1)).toResponseDTO(any(ItemEntity.class));
    }

    @Test
    void deleteItemById() {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(MockItemFactory.getItemEntity()));

        DeleteResponseDTO expected = MockGeneralFactory.getDeleteResponseDTO(TestConstants.DELETE_MESSAGE, 1);
        DeleteResponseDTO actual = itemService.deleteItemById(anyLong());
        Assert.assertEquals(expected, actual);

        verify(itemRepository, times(1)).findById(anyLong());
        verify(itemRepository, times(1)).deleteById(anyLong());
    }
}
