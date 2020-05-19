package com.city.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/18
 * Time: 21:30
 * Description: No Description
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.city") //扫描swaggerConfig启动控制器配置类--使用/swagger-ui.html访问这个服务的所有api
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
