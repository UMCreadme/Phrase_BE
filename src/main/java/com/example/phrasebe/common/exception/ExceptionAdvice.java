package com.example.phrasebe.common.exception;

import com.example.phrasebe.common.response.ApiResponse;
import com.example.phrasebe.common.status.ErrorStatus;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity<ApiResponse> validation(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));
        return ApiResponse.onFailure(ErrorStatus.VALIDATION_ERROR, errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        e.printStackTrace();

        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.onFailure(ErrorStatus.VALIDATION_ERROR, errorMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e
    ) {
        e.printStackTrace();

        String errorMessage = e.getParameterType() + " 타입의 " + e.getParameterName() + " 파라미터가 없습니다.";
        return ApiResponse.onFailure(ErrorStatus.VALIDATION_ERROR, errorMessage);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        e.printStackTrace();

        return ApiResponse.onFailure(ErrorStatus._NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        e.printStackTrace();

        return ApiResponse.onFailure(ErrorStatus._INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse> handleGeneralException(GeneralException e) {
        e.printStackTrace();

        return ApiResponse.onFailure(e.getErrorStatus(), e.getMessage());
    }
}
