package com.fresh.restapi.services.item;


import com.fresh.restapi.dtos.requests.item.ItemRequestDTO;
import com.fresh.restapi.dtos.requests.item.ItemUpdateRequestDTO;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> getAllItems();
    ItemResponseDTO getItemById(Long id);
    ItemResponseDTO createNewItem(ItemRequestDTO request);
    ItemResponseDTO updateItemById(Long id, ItemRequestDTO request);
    DeleteResponseDTO deleteItemById(Long id );
}
