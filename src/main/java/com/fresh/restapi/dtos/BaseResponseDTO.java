package com.fresh.restapi.dtos;

import lombok.*;

import java.util.ArrayList;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class BaseResponseDTO<T> {
    private Object data;
    private T message;

    public BaseResponseDTO(Object any) {
        this.data = any;
        this.message = null;
    }

    public BaseResponseDTO(Exception e) {
        this.data = null;
        this.message = (T) e.getMessage();
    }

    public BaseResponseDTO(ArrayList<String> errors) {
        this.data = null;
        this.message = (T) errors;
    }
}
