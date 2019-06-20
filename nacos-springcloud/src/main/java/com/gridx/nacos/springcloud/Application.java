package com.gridx.nacos.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Nacos 控制台添加配置：
 * <p>
 * Data ID nacosdemo.properties
 * <p>
 * Group：DEFAULT_GROUP
 *
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
