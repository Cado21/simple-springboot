package com.fresh.restapi.dtos.responses.item;

import com.fresh.restapi.dtos.BaseEntityResponseDTO;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO extends BaseEntityResponseDTO {
    private String name;
    private String description;
    private String thumbnail;
    private Integer quantity;
}
