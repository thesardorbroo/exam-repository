package com.example.uz.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto<T> {

    private Integer code;

    private Boolean success;

    private String message;

    private T data;
}
