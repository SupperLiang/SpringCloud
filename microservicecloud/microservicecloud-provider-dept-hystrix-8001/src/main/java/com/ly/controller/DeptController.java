package com.ly.controller;

import com.ly.entities.Dept;
import com.ly.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "processHystrix_Get")//服务熔断后需要执行的方法
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.get(id);
        if (dept == null) {
            throw new RuntimeException("该ID：" + id + "没有数据");
        }
        return deptService.get(id);
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id)
                .setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list() {
        return deptService.list();
    }

    @GetMapping(value = "/dept/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        System.out.println(services);
        List<ServiceInstance> instances = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
}
