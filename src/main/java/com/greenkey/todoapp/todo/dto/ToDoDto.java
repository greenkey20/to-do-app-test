package com.greenkey.todoapp.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

// 2022.12.12(월) 16h20 Post/PatchDto 코드 중복이 거슬리긴 한데, 어떻게 반복을 줄일 수 있을까?
public class ToDoDto {
    @Getter
    @AllArgsConstructor
//    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Post {
        @NotBlank(message = "할 일 항목은 공백이 아니어야 합니다")
        @Size(max = 700)
        private String title;

        @JsonProperty("todo_order")
        @PositiveOrZero
        private Integer toDoOrder;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long id;

        @NotBlank(message = "할 일 항목은 공백이 아니어야 합니다")
        @Size(max = 700)
        private String title;

        @PositiveOrZero
        @JsonProperty("todo_order")
        private Integer toDoOrder;

        private boolean completed;

        public void setId(long id) {
            this.id = id;
        }
    }

    // 2022.12.12(월) 19h30 추가
    @Getter
    @AllArgsConstructor
//    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Response {
        private long id;
        private String title;

        @JsonProperty("todo_order")
        private Integer toDoOrder;
        private boolean completed;

        // 2022.12.13(화) 11h5 api 테스팅용 추가
        @Override
        public String toString() {
            return "Response{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", toDoOrder=" + toDoOrder +
                    ", completed=" + completed +
                    '}';
        }
    }
}
