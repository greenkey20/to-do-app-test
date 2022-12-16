package com.greenkey.todoapp;

import com.greenkey.todoapp.todo.repository.ToDoRepository;
import com.greenkey.todoapp.todo.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// 2022.12.16(금) 21h10 작성 시작
@ExtendWith(MockitoExtension.class)
public class ToDoServiceMockTest {
    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @Test
    void createTodoTest() {

    }
}
