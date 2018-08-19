package com.rabbit.samples.consumerservice.configs;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;


@Configuration("discoveryConfig")
@EnableDiscoveryClient
public class DiscoveryConfig {

	// no-op
}
