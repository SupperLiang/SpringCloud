package com.ly.cfgbean;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule myRule() {
        //轮询
        return new RoundRobinRule();
        //随机
        // return new RandomRule();
        //过滤掉那些因为一直连接失败的被标记为circuit tripped的后端server，并过滤掉那些高并发的的后端server（active connections 超过配置的阈值）
        // return new AvailabilityFilteringRule();
        //根据响应时间分配一个weight，响应时间越长，weight越小，被选中的可能性越低。
        // return new WeightedResponseTimeRule();
        //对选定的负载均衡策略机上重试机制。
        // return new RetryRule();
        //选择一个最小的并发请求的server
        // return new BestAvailableRule();
        //复合判断server所在区域的性能和server的可用性选择server
        // return new ZoneAvoidanceRule();
    }
}
