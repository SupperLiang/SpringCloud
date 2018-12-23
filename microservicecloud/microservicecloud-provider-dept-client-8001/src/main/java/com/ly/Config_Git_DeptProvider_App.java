package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//开启Eureka客户端
@EnableDiscoveryClient//开启服务发现
public class Config_Git_DeptProvider_App {
    public static void main(String[] args) {
        SpringApplication.run(Config_Git_DeptProvider_App.class, args);
    }
}
