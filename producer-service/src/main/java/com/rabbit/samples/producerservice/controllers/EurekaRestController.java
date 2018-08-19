package com.rabbit.samples.producerservice.controllers;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
@RestController("eurekaRestController")
@RequestMapping("/eureka")
class EurekaRestController {

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	private EurekaClient eurekaClient;

	@GetMapping("/apps")
	public Applications getEurekaApps() {

		log.info("Getting Eureka applications...");

		return getEurekaClient().getApplications();
	}

}
