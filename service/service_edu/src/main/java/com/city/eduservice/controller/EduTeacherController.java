package com.city.eduservice.controller;


import com.city.commonutils.Result;
import com.city.eduservice.entity.EduTeacher;
import com.city.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author city
 * @since 2020-05-18
 */
@Api(description ="讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    /**
     * controller->service->mapper->entity
     * mybatis逆向生成代码后无需配置xml和接口的映射，同时xml和接口在继承了BaseMapper的同时还可以自己再写sql
     * 原本只是生成mapper-sql操作现在service都生成了
     * service也调用mapper自带了一些简单的逻辑处理
     */


    @Autowired
    private EduTeacherService eduTeacherService;

    //1、查询教师列表
    //rest风格，查询用getmapper，插入用postmapper，改用putmapper，删用deletemaper
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/queryAllTeacher")
    public Result queryAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("allTeacher",list);
    }

    //2、逻辑删除
    @ApiOperation(value = "根据id删除讲师列表")
    @DeleteMapping("/delectTeacherById/{id}")//地址直接写{id}--那就是直接访问/eduservice/edu-teacher/1--delectmapping方式无法直接测试，get和post才能直接测试
    public Result delectTeacherById(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){//@PathVariable--path路径，从路径中获取值，RequestParam--post请求中的参数获取
        boolean b = eduTeacherService.removeById(id);
        if (b==true) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

