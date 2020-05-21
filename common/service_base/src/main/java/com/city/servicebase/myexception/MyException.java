package com.city.servicebase.myexception;

import com.city.servicebase.myexception.exceptionenum.MyExceptionEnumImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/21
 * Time: 20:18
 * Description: No Description
 *
 * 自定义异常，代码中遇到异常时抛出自定义异常，然后给全局异常处理自定义异常返回给前端
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;

    public MyException(MyExceptionEnumImpl myExceptionEnum){
        this.code=myExceptionEnum.getCode();
        this.msg=myExceptionEnum.getMsg();
    }
}
