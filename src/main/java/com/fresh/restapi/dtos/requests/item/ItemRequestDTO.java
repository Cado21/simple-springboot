package com.fresh.restapi.dtos.requests.item;


import lombok.*;

import javax.validation.constraints.NotNull;


@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "description is required")
    private String description;

    @NotNull(message = "thumbnail is required")
    private String thumbnail;

    @NotNull(message = "quantity is required")
    private Integer quantity;
}
