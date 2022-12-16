package com.greenkey.todoapp.todo.service;

import com.greenkey.todoapp.exception.BusinessLogicException;
import com.greenkey.todoapp.exception.ExceptionCode;
import com.greenkey.todoapp.todo.entity.ToDo;
import com.greenkey.todoapp.todo.repository.ToDoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 2022.12.12(월) 16h10 클래스 생성
@Transactional
@Service
public class ToDoService {
    // 2022.12.12(월) 19h50 구현
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    // 2022.12.13(화) 2h
    public ToDo createToDo(ToDo toDo) {
        verifyExistsTodo(toDo.getTitle()); // 여기까지 왔다면 중복되는 할 일 항목 없으니까, 새로 등록 가능
//        ToDo createdToDo = toDoRepository.save(toDo); // db 내부 시스템?에 의해 id(primary key) 자동 부여됨 +
//        return createdToDo;
        return toDoRepository.save(toDo);
    }

    private void verifyExistsTodo(String title) {
        Optional<ToDo> toDo = toDoRepository.findByTitle(title);
        if (toDo.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
        }
    }

    // 2022.12.13(화) 13h20 -> 15h40 @Transactional annotation 안 붙이고 테스팅했었음을 알게 됨
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public ToDo updateToDo(ToDo toDo) {
        // 수정하려고 하는 할 일 항목이 DB에 있는 것인지 먼저 확인
        ToDo findToDo = findVerifiedToDo(toDo.getId());

        Optional.ofNullable(toDo.getTitle()).ifPresent(title -> findToDo.setTitle(title));
        Optional.ofNullable(toDo.getCompleted()).ifPresent(completed -> findToDo.setCompleted(completed));
        Optional.ofNullable(toDo.getToDoOrder()).ifPresent(toDoOrder -> findToDo.setToDoOrder(toDoOrder));

        return toDoRepository.save(findToDo);
    }

    @Transactional(readOnly = true)
    public ToDo findVerifiedToDo(long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);

        ToDo findToDo = optionalToDo.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findToDo;
    }

    // 2022.12.13(화) 15h35
    public Page<ToDo> findToDos(int page, int size) {
        return toDoRepository.findAll(PageRequest.of(page, size, Sort.by("toDoOrder")));
    }

    // 2022.12.13(화) 23h
    public List<ToDo> findToDos() {
        return toDoRepository.findAll();
    }

    // 2022.12.13(화) 16h35
    public ToDo findToDo(long id) {
        return findVerifiedToDo(id);
    }

    // 2022.12.13(화) 16h15
    public void deleteToDos() {
        toDoRepository.deleteAllInBatch();
    }

    // 2022.12.13(화) 16h40
    public void deleteToDo(long id) {
        ToDo findToDo = findVerifiedToDo(id); // 테스트 후 로직 보강을 위해 추가
        toDoRepository.deleteById(findToDo.getId());
    }
}
