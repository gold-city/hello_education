package com.city.commonutils;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/19
 * Time: 15:44
 * Description: No Description
 *
 * 定义返回状态码
 * 统一返回结果格式为
 * json
 * "success":true,
 * "code":20000,
 * "message":"成功"，
 * "data":{....[...]}
 */
public interface ResultCode {
    /**
     *  public static final 修饰符对于接口来说是多余的，可以省略
     */
    Integer SUCCESS=20000;//成功
    Integer ERROR=20001;//失败
}
