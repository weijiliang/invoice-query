package com.dbic.vms.invoicequery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

   @GetMapping("/getApplyMsg")
    public  String getApplyMsg() {

/*
        //1、第一种方式
        RestTemplate restTemplate = new RestTemplate();
        String response="";
         response = restTemplate.getForObject("http://localhost:8082/msg", String.class);
        log.info("respones={}", response);
        return  response;
*/

       //2、第二种方式  通过loadBalancerClient 以及服务名从注册中心来动态获取ip及端口
       RestTemplate restTemplate = new RestTemplate();
       ServiceInstance serviceInstance=loadBalancerClient.choose("INVOICE-APPLY");
       String url=String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
       String response="";
       response = restTemplate.getForObject(url, String.class);
       log.info("respones={}", response);
       return  response;


    }
}
