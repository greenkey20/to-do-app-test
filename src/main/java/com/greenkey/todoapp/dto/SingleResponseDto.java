package com.greenkey.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 2022.12.12(월) 19h45
@Getter
@AllArgsConstructor
public class SingleResponseDto<T> {
    private T data;
}
