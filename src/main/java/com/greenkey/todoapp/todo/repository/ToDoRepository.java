package com.greenkey.todoapp.todo.repository;

import com.greenkey.todoapp.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 2022.12.12(월) 19h50 클래스 생성
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    // 2022.12.13(화) 2h10
    Optional<ToDo> findByTitle(String title);
}
