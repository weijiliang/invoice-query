package com.dbic.vms.invoicequery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置中心测试
 */
@RestController
@RequestMapping("/env")
@RefreshScope
public class ConfigtestController {
    @Value("${env}")
    private  String env;
    @GetMapping("/print")
    public String print(){
        return  env;
    }
}
