package com.city.servicebase.myexception.exceptionenum;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/21
 * Time: 21:58
 * Description: No Description
 *
 * 定义接口为了让别的模块的异常处理枚举继承
 */
public interface IMyExceptionEnum {
    Integer getCode();
    String getMsg();
}
