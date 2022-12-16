package com.greenkey.todoapp.todo.controller;

import com.greenkey.todoapp.dto.MultiResponseDto;
import com.greenkey.todoapp.dto.SingleResponseDto;
import com.greenkey.todoapp.todo.mapper.ToDoMapper;
import com.greenkey.todoapp.todo.dto.ToDoDto;
import com.greenkey.todoapp.todo.entity.ToDo;
import com.greenkey.todoapp.todo.repository.ToDoRepository;
import com.greenkey.todoapp.todo.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

// 2022.12.12(월) 16h5
@RestController
@RequestMapping("/v1/todos")
@Validated
@Slf4j
@CrossOrigin
public class ToDoController {
    private final ToDoRepository toDoRepository;
    private final ToDoService toDoService;
    private final ToDoMapper mapper;

    public ToDoController(ToDoService toDoService, ToDoMapper mapper,
                          ToDoRepository toDoRepository) {
        this.toDoService = toDoService;
        this.mapper = mapper;
        this.toDoRepository = toDoRepository;
    }

    // [create] 할 일 항목 등록 -> 19h5 이어서 작성
    @PostMapping
    public ResponseEntity postToDo(@Valid @RequestBody ToDoDto.Post requestBody) {
//        System.out.println(requestBody.getToDoOrder()); // TODO

        ToDo createdToDo = toDoService.createToDo(mapper.toDoPostDtoToToDo(requestBody));
//        System.out.println(createdToDo); // TODO ToDo{id=1, title='운동하기', toDoOrder=null, completed=false}

        ToDoDto.Response response = mapper.toDoToToDoResponse(createdToDo);
//        System.out.println(response); // TODO Response{id=0, title='null', toDoOrder=null, completed=false}

//        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
        // 2022.12.13(화) 22h30 API 명세 맞춰서 Todo-backend 사이트에서 확인하기 위해 return 내용 수정
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2022.12.13(화) 15h 구현 시작
    // [read] 할 일 목록 전체 조회 + pagination?
    @GetMapping
    public ResponseEntity getToDos(/*@Positive @RequestParam int page, @Positive @RequestParam int size*/) {
        /*
        Page<ToDo> pageToDos = toDoService.findToDos(page - 1, size);
        List<ToDo> toDos = pageToDos.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.toDosToToDoResponses(toDos), pageToDos), HttpStatus.OK);
         */
        // 2022.12.13(화) 23h 수정
        List<ToDo> toDos = toDoService.findToDos();
        return new ResponseEntity<>(mapper.toDosToToDoResponses(toDos), HttpStatus.OK);
    }

    // 2022.12.13(화) 16h30
    // [read] 할 일 목록 1개 조회 <- 특정 id 입력/요청받음
    @GetMapping("/{id}")
    public ResponseEntity getToDo(@PathVariable @Positive long id) {
        ToDo toDo = toDoService.findToDo(id);
//        return new ResponseEntity<>(new SingleResponseDto<>(mapper.toDoToToDoResponse(toDo)), HttpStatus.OK);
        // 2022.12.13(화) 23h25 수정
        return new ResponseEntity<>(mapper.toDoToToDoResponse(toDo), HttpStatus.OK);
    }

    // 2022.12.12(월) 17h45 문득 드는 생각 = update 대상 속성별 메서드 별도로 만들 필요 없다?
    // 2022.12.13(화) 13h5 구현 시작
    @PatchMapping("/{id}")
    public ResponseEntity patchToDo(@PathVariable @Positive long id, @Valid @RequestBody ToDoDto.Patch requestBody) {
        requestBody.setId(id);
        ToDo modifiedToDo = toDoService.updateToDo(mapper.toDoPatchDtoToToDo(requestBody));
//        return new ResponseEntity<>(new SingleResponseDto<>(mapper.toDoToToDoResponse(modifiedToDo)), HttpStatus.OK);
        // 2022.12.13(화) 23h30 수정
        return new ResponseEntity<>(mapper.toDoToToDoResponse(modifiedToDo), HttpStatus.OK);
    }

    // 2022.12.13(화) 16h10
    // [delete] 할 일 목록 전체 삭제
    @DeleteMapping
    public ResponseEntity deleteToDos() {
        toDoService.deleteToDos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 2022.12.13(화) 16h35
    // [delete] 할 일 목록 1개 삭제 <- 특정 id 입력/요청받음
    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable @Positive long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
