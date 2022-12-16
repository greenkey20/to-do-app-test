package com.greenkey.todoapp;

import com.google.gson.Gson;
import com.greenkey.todoapp.todo.mapper.ToDoMapperReference;
import com.greenkey.todoapp.todo.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

// 2022.12.16(금) 16h45 클래스 생성
@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    // 2022.12.16(금) 21h15 이어서 작성
    @MockBean
    private ToDoService toDoService;

    @Autowired
    private ToDoMapperReference toDoMapperReference;

    @Test
    void postToDoTest() {

    }
}
