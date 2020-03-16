package com.fresh.restapi.converters;

import com.fresh.restapi.dtos.BaseResponseDTO;


public interface ResponseConverter {
    static BaseResponseDTO anyToBasicResponse(Object any) {
        return BaseResponseDTO.builder()
                .data(any)
                .message("success")
                .build();
    }
}
