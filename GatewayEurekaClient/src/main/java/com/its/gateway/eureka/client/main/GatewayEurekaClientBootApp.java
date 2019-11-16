package com.its.gateway.eureka.client.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tuannx2
 *
 */
@EnableEurekaClient
@ComponentScan("com.its.gateway.*")
@EnableAutoConfiguration
@SpringBootApplication
public class GatewayEurekaClientBootApp {
	public static void main(String[] args) {
		SpringApplication.run(GatewayEurekaClientBootApp.class, "");
	}
}
