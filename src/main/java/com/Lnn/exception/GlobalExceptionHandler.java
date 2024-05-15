package com.Lnn.exception;

import com.Lnn.constants.HttpConstants;
import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result ExceptionHandler(RuntimeException e)
    {
        e.printStackTrace();
        return Result.error(HttpConstants.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result RoleExceptionHandler(AccessDeniedException e)
    {
        e.printStackTrace();
        return Result.error(HttpConstants.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
