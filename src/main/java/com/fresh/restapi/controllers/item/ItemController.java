package com.fresh.restapi.controllers.item;


import com.fresh.restapi.aspect.CheckPermission;
import com.fresh.restapi.aspect.NeedToken;
import com.fresh.restapi.constants.API;
import com.fresh.restapi.converters.ResponseConverter;
import com.fresh.restapi.dtos.BaseResponseDTO;
import com.fresh.restapi.dtos.requests.item.ItemRequestDTO;
import com.fresh.restapi.dtos.requests.item.ItemUpdateRequestDTO;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.item.ItemResponseDTO;
import com.fresh.restapi.services.item.ItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = API.V1 + API.ITEMS, produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    @Autowired
    private ItemService itemService;

    @CheckPermission
    @GetMapping
    public ResponseEntity getAllItems() {
        List<ItemResponseDTO> dtoList = itemService.getAllItems();
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dtoList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @GetMapping(value = "/{id}")
    public ResponseEntity getItemById(
            @PathVariable Long id
    ) {
        ItemResponseDTO dto = itemService.getItemById(id);
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @PostMapping
    public ResponseEntity createNewItem(
            @Valid @RequestBody ItemRequestDTO request
    ) {
        ItemResponseDTO dto = itemService.createNewItem(request);
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @PutMapping(value = "/{id}")
    public ResponseEntity updateItemById(
            @PathVariable Long id,
            @RequestBody ItemUpdateRequestDTO request
    ) {
        System.out.println(request);
        ItemResponseDTO dto = itemService.updateItemById(id, request);

        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteItemById(
            @PathVariable Long id
    ) {
        DeleteResponseDTO dto = itemService.deleteItemById(id);
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
