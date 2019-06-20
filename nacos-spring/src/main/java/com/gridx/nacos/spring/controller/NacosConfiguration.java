package com.gridx.nacos.spring.controller;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     dataId  Gridx_default
 * <p/>
 * <p>
 *     serverAddr 10.100.36.189:8848
 * </p>
 * <p>
 *     namespace : default(public) nacos spring原生不支持自定namespace的接入，只有集成spring cloud才支持
 * </p>
 *
 */
@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "10.100.36.189:8848"))
@NacosPropertySource(dataId = "Gridx_default", autoRefreshed = true)
public class NacosConfiguration {

    @NacosValue(value = "${hello:leo}", autoRefreshed = true)
    public String hello;

    @NacosValue(value = "${gridx:nanjing}", autoRefreshed = true)
    public String gridx;

}
