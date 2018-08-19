package com.rabbit.samples.consumerservice.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
@RestController("serviceInstancesRestController")
@RequestMapping("/discovery")
class ServiceInstancesRestController {

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/instances")
	public List<ServiceInstance> getInstances() {

		log.info("Getting service instances for app {}...", getAppName());

		return getDiscoveryClient().getInstances(getAppName());
	}

	@GetMapping("/services")
	public List<String> getServices() {

		log.info("Getting services...");

		return getDiscoveryClient().getServices();
	}

}
