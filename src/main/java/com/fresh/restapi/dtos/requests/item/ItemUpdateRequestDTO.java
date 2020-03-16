package com.fresh.restapi.dtos.requests.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequestDTO {
    private String name;
    private String description;
    private String thumbnail;
    private Integer quantity;
}
