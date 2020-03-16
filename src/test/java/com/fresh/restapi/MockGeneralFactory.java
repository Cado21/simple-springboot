package com.fresh.restapi;

import com.fresh.restapi.dtos.responses.DeleteResponseDTO;

public class MockGeneralFactory {
    public static DeleteResponseDTO getDeleteResponseDTO(String message, int deleteCount) {
        return DeleteResponseDTO
                .builder()
                .message(message)
                .deletedCount(deleteCount)
                .build();
    }
}
