package com.ietunnel.riskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: zxg
 * @Date:2021/12/21/23:12
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ietunnel"})
@EnableSwagger2
public class RiskApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiskApplication.class,args);
    }
}
