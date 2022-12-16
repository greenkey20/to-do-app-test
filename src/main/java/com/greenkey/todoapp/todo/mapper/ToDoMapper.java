package com.greenkey.todoapp.todo.mapper;

import com.greenkey.todoapp.todo.dto.ToDoDto;
import com.greenkey.todoapp.todo.entity.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

// 2022.12.12(월) 19h15
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ToDoMapper {
    ToDo toDoPostDtoToToDo(ToDoDto.Post requestBody);
    ToDo toDoPatchDtoToToDo(ToDoDto.Patch requestBody);
    ToDoDto.Response toDoToToDoResponse(ToDo toDo);
    List<ToDoDto.Response> toDosToToDoResponses(List<ToDo> toDos);
}
