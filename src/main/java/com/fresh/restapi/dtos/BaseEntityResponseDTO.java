package com.fresh.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityResponseDTO {
    private Long id;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
