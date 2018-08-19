package com.rabbit.samples.producerservice.configs;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;


@Configuration("discoveryConfig")
@EnableDiscoveryClient
public class DiscoveryConfig {

	// no-op
}
