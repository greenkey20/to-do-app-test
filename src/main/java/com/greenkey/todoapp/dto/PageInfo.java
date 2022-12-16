package com.greenkey.todoapp.dto;

// 2022.12.13(화) 15h35

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPage;
}
