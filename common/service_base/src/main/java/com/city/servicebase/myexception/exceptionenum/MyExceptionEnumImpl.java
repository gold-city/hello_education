package com.city.servicebase.myexception.exceptionenum;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/21
 * Time: 22:31
 * Description: No Description
 */
public enum  MyExceptionEnumImpl implements IMyExceptionEnum{
    NOT_Exception(20001,"未知异常");

    private Integer code;
    private String msg;

    MyExceptionEnumImpl(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
