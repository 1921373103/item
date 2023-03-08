package com.lin.item.common.advice;

import com.lin.item.common.entity.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: L
 * @Date: 2023/3/7 21:38
 * @Desc: 参数异常拦截
 */
@RestControllerAdvice(basePackages = "com.lin.item")
public class ParameterValidAdvice {

    /**
     * 参数验证异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Result bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, Object> map = new HashMap<>();
        AtomicReference<String> result = new AtomicReference<>("");
        bindingResult.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
            result.set(fieldError.getDefaultMessage());
        });
        return Result.error(result.get());
    }

}
