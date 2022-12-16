package com.greenkey.todoapp.todo.mapper;

import com.greenkey.todoapp.todo.dto.ToDoDto;
import com.greenkey.todoapp.todo.entity.ToDo;
import com.greenkey.todoapp.todo.repository.ToDoRepository;
import org.springframework.stereotype.Component;

// 2022.12.16(금) 16h25 동료수강생분 코드 참고해서 추가
@Component
public class ToDoMapperReference {
    private final ToDoRepository toDoRepository;

    public ToDoMapperReference(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo toDoPostDtoToToDo(ToDoDto.Post requestBody) {
        if (requestBody == null) {
            return null;
        }

        ToDo toDo = ToDo.builder()
                .title(requestBody.getTitle())
                .toDoOrder(requestBody.getToDoOrder())
                .build();

        return toDo;
    }

}
