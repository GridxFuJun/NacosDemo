package com.gridx.nacos.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {


    @Value(value = "${hello:all}")
    private String hello;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String getConfig() {
        return hello;
    }

}
