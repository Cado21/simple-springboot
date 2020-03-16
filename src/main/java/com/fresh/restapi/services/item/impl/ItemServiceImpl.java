package com.fresh.restapi.services.item.impl;

import com.fresh.restapi.converters.ItemConverter;
import com.fresh.restapi.dtos.requests.item.ItemRequestDTO;
import com.fresh.restapi.dtos.requests.item.ItemUpdateRequestDTO;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.exceptions.NotFoundException;
import com.fresh.restapi.models.item.ItemEntity;
import com.fresh.restapi.repositories.ItemRepository;
import com.fresh.restapi.services.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private ItemConverter itemConverter;


    @Override
    public List<ItemResponseDTO> getAllItems() {
        List<ItemEntity> items = itemRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return itemConverter.toListResponseDTO(items);
    }

    @Override
    public ItemResponseDTO getItemById(Long id) {
        Optional<ItemEntity> item = itemRepo.findById(id);
        ItemEntity itemFound = item.orElseThrow(() ->
                new NotFoundException("Item with ID " + id + " Not Found")
        );
        return itemConverter.toResponseDTO(itemFound);
    }

    @Override
    public ItemResponseDTO createNewItem(ItemRequestDTO request) {
        ItemEntity newItem = new ItemEntity();

        newItem.setName(request.getName());
        newItem.setDescription(request.getDescription());
        newItem.setThumbnail(request.getThumbnail());
        newItem.setQuantity(request.getQuantity());

        ItemEntity createdItem = itemRepo.save(newItem);
        return itemConverter.toResponseDTO(createdItem);
    }

    @Override
    public ItemResponseDTO updateItemById(Long id, ItemUpdateRequestDTO request) {
        Optional<ItemEntity> optionalItem = itemRepo.findById(id);

        ItemEntity newItem = optionalItem.orElseThrow(() ->
               new NotFoundException("item with id " + id + " not found!")
        );

        if (!ObjectUtils.isEmpty(request.getName())) newItem.setName(request.getName());
        if (!ObjectUtils.isEmpty(request.getDescription())) newItem.setDescription(request.getDescription());
        if (!ObjectUtils.isEmpty(request.getThumbnail())) newItem.setThumbnail(request.getThumbnail());
        if (request.getQuantity() != null) newItem.setQuantity(request.getQuantity());

        ItemEntity updatedItem = itemRepo.save(newItem);
        return itemConverter.toResponseDTO(updatedItem);
    }

    @Override
    public DeleteResponseDTO deleteItemById(Long id) {
        Optional<ItemEntity> optionalItem = itemRepo.findById(id);
        int deletedCount = 0;
        if (optionalItem.isPresent()) {
            deletedCount = 1;
            itemRepo.deleteById(id);
        }
        return new DeleteResponseDTO("Delete item success", deletedCount);
    }
}
