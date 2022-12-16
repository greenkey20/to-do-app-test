package com.greenkey.todoapp.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

// 2022.12.13(화) 15h30
@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
