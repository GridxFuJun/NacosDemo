package com.gridx.nacos.spring.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private NacosConfiguration conf;


    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() throws NacosException {

        return "hello : " + conf.hello +" , this is " + conf.gridx;
    }



}
