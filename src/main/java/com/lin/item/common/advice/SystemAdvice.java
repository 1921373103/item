package com.lin.item.common.advice;

import com.lin.item.common.constant.ExceptionConstant;
import com.lin.item.common.entity.Result;
import com.lin.item.common.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: L
 * @Date: 2023/3/7 21:41
 * @Desc: 系统业务异常
 */
@RestControllerAdvice(basePackages = "com.lin.item")
public class SystemAdvice {

    /**
     * 自定义业务异常拦截
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = CustomException.class)
    public Result customException(CustomException e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    /**
     * 系统未知异常拦截
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error(ExceptionConstant.SYSTEM_ERROR);
    }
}
