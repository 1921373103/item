package com.lin.item.common.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.hutool.http.HttpStatus;
import com.lin.item.common.constant.ExceptionConstant;
import com.lin.item.common.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: L
 * @Date: 2023/3/9 14:33
 * @Desc: 权限异常拦截处理类
 */
@RestControllerAdvice(basePackages = "com.lin.item")
public class AuthCheckAdvice {

    /**
     * 未登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotLoginException.class)
    public Result notLoginException(NotLoginException e) {
        e.printStackTrace();
        return Result.error(HttpStatus.HTTP_NOT_IMPLEMENTED, ExceptionConstant.TOKEN_EXCEPTION);
    }

}
