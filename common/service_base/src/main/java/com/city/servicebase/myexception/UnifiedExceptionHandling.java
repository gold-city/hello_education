package com.city.servicebase.myexception;

import com.city.commonutils.Result;
import com.city.servicebase.myexception.exceptionenum.MyExceptionEnumImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/21
 * Time: 18:04
 * Description: No Description
 *
 * 统一异常处理
 *
 * @ControllerAdvice，增强控制器，可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
 * 1、全局异常处理
 * 2、全局数据绑定
 * 3、全局数据预处理
 */
@ControllerAdvice
@Slf4j
public class UnifiedExceptionHandling {
    //1、全局异常捕捉
    @ExceptionHandler(Exception.class)//定义捕获什么异常Exception为全部异常的父类
    @ResponseBody //返回json数据
    public Result error(Exception e){
        //System.out.println(e.getClass())获取类判断;
        e.printStackTrace();//控制台打印堆栈跟踪发生的错误
        return Result.error().code(MyExceptionEnumImpl.NOT_Exception.getCode()).message(MyExceptionEnumImpl.NOT_Exception.getMsg());
    }

    //2、特定异常捕捉，把捕捉的全局异常设为特定异常就行
    //完全可以只写一个异常处理方法，判断e的异常类型再处理，这样就不用写三个方法

    //3、自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        e.printStackTrace();
        log.error(e.getMessage()+" message: "+e.getMsg());
        return Result.error().message(e.getMsg()).code(e.getCode());
    }
}