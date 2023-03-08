package com.lin.item.common.entity;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.lin.item.common.constant.PromptConstant;

import java.util.LinkedHashMap;

/**
 * @Author: L
 * @Date: 2023/3/7 21:39
 * @Desc: 返回结果集
 */
public class Result extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE = "code";

    public static final String MSG = "msg";

    public static final String DATA = "data";

    public Result() {

    }

    /**
     * 初始化返回对象
     * @param code
     * @param msg
     */
    public Result(Integer code, String msg) {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * 初始化返回对象
     * @param code
     * @param msg
     * @param data
     */
    public Result(Integer code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if(ObjectUtil.isNotNull(data)) {
            super.put(DATA, data);
        }
    }

    /**
     * 无参返回对象
     * @return
     */
    public static Result success() {
        return new Result(HttpStatus.HTTP_OK, PromptConstant.SUCCESS_MSG);
    }

    /**
     * 自定义成功消息
     * @param msg
     * @return
     */
    public static Result success(String msg) {
        return new Result(HttpStatus.HTTP_OK, msg, null);
    }

    /**
     * 自定义成功数据
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return Result.success(PromptConstant.SELECT_SUCCESS, data);
    }


    /**
     * 自定义返回对象
     * @param msg
     * @param data
     * @return
     */
    public static Result success(String msg, Object data) {
        return new Result(HttpStatus.HTTP_OK, msg, data);
    }


    /**
     * 无参返回对象
     * @return
     */
    public static Result error() {
        return new Result(HttpStatus.HTTP_INTERNAL_ERROR, PromptConstant.ERROR_MSG);
    }

    /**
     * 自定义异常消息
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        return new Result(HttpStatus.HTTP_INTERNAL_ERROR, msg, null);
    }

    /**
     * 自定义异常消息
     * @return
     */
    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    /**
     * 自定义异常数据
     * @param data
     * @return
     */
    public static Result error(Object data) {
        return Result.error(PromptConstant.ERROR_MSG, data);
    }

    /**
     * 自定义返回对象
     * @param msg
     * @param data
     * @return
     */
    public static Result error(String msg, Object data) {
        return new Result(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }

    /**
     * 响应返回结果
     * @param result 是否成功
     * @return 操作结果
     */
    public static Result toAjax(Boolean result) {
        return result ? Result.success() : Result.error();
    }
}
