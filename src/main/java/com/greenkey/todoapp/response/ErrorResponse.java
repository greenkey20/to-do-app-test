package com.greenkey.todoapp.response;

import com.greenkey.todoapp.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

// 2022.12.13(화) 3h5
@Getter
public class ErrorResponse {
    private int status;
    private String message;

    // 2022.12.13(화) 10h5 계속 진행
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // GlobalExceptionAdvice 클래스에서 아래 of() 부재로 발생하는 오류에서 자동 생성함
    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    // 2022.12.13(화) 11h5 api 테스팅용 추가 -> 사실 이 클래스의 toString() 만들려고 한 것 아님(나의 실수)
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
