package com.city.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.city.commonutils.Result;
import com.city.eduservice.entity.EduTeacher;
import com.city.eduservice.entity.vo.ConditionsTeacher;
import com.city.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    //3、讲师分页
    @ApiOperation(value = "讲师分页")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Result pageTeacher(@PathVariable Long current, @PathVariable Long limit){
        //创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        //调用方法实现分页
        //调用方式时，底层封装，把该页数据封装到pageTeacher中
        eduTeacherService.page(eduTeacherPage,null);
        //获取总数据数
        long total = eduTeacherPage.getTotal();
        //获取该页的数据
        List<EduTeacher> eduTeacherList = eduTeacherPage.getRecords();
        //返回方法一
        /*Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",total);
        map.put("eduTeacherList",eduTeacherList);
        return Result.ok().data(map);*/
        //返回方法二
        return Result.ok().data("total",total).data("eduTeacherList",eduTeacherList);
    }

    //4、带条件的讲师分页
    @ApiOperation(value = "带条件的讲师分页")
    @PostMapping("/pageTeacherConditions/{current}/{limit}")
    public Result pageTeacherConditions(@PathVariable Long current, @PathVariable Long limit,@RequestBody(required = false) ConditionsTeacher conditionsTeacher){
        //创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        //调用方法实现分页
        //构建条件
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        //多条件组合，判空，不空则拼接
        if (!StringUtils.isEmpty(conditionsTeacher.getName())){
            queryWrapper.like("name",conditionsTeacher.getName());
        }
        if (!StringUtils.isEmpty(conditionsTeacher.getLevel())){
            queryWrapper.eq("level",conditionsTeacher.getLevel());
        }
        //使用区间的话两个值必须存在，使用大于小于的话可以单独查询
        if (!StringUtils.isEmpty(conditionsTeacher.getBegin())){
            queryWrapper.ge("gmt_create",conditionsTeacher.getBegin());
        }
        if (!StringUtils.isEmpty(conditionsTeacher.getEnd())){
            queryWrapper.le("gmt_modified",conditionsTeacher.getEnd());
        }
        //调用方式时，底层封装，把该页数据封装到pageTeacher中
        eduTeacherService.page(eduTeacherPage,queryWrapper);
        //获取总数据数
        long total = eduTeacherPage.getTotal();
        //获取该页的数据
        List<EduTeacher> eduTeacherList = eduTeacherPage.getRecords();
        return Result.ok().data("total",total).data("eduTeacherList",eduTeacherList);
    }

    //5、添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //5、根据id查讲师
    @ApiOperation(value = "根据id查讲师")
    @GetMapping("/queryTeacherById/{id}")
    public Result queryTeacherById(@PathVariable String id){
        EduTeacher byId = eduTeacherService.getById(id);
        if (!StringUtils.isEmpty(byId)) {
            return Result.ok().data("byIdTeacher",byId);
        }else{
            return Result.error();
        }
    }

    //6、修改
    @ApiOperation(value = "更新讲师")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

