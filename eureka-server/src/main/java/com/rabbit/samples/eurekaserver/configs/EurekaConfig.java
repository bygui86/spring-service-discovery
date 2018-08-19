package com.rabbit.samples.eurekaserver.configs;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;


@Configuration("eurekaConfig")
@EnableEurekaServer
public class EurekaConfig {

	// no-op
}
