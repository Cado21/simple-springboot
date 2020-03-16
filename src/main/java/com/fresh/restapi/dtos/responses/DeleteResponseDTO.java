package com.fresh.restapi.dtos.responses;


import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponseDTO {
    private String message;
    private Integer deletedCount;
}
