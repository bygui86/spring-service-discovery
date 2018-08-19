package com.rabbit.samples.consumerservice.configs;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.rabbit.samples.consumerservice.constants.ServicesNamesConstants;
import com.rabbit.samples.consumerservice.feign.clients.ProducerServiceGreetingsClient;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration("feignConfig")
public class FeignConfig {

	@Bean
	@Autowired
	public ProducerServiceGreetingsClient producerServiceGreetingsClient(final EurekaClient eurekaClient) {

		final InstanceInfo instanceInfo = getProducerServiceInstanceInfo(eurekaClient);
		final String hostname = instanceInfo.getHostName();
		final int port = instanceInfo.getPort();

		log.debug("ProducerService hostname {}, port {}", hostname, port);

		return Feign.builder()
				.client(new OkHttpClient())
				.encoder(new Encoder.Default())
				.decoder(new Decoder.Default())
				// .encoder(new GsonEncoder())
				// .decoder(new GsonDecoder())
				.logger(new Slf4jLogger(ProducerServiceGreetingsClient.class))
				.logLevel(Logger.Level.FULL)
				.target(ProducerServiceGreetingsClient.class,
						buildProducerServiceGreetingsUrl(
								hostname,
								port
						)
				);
	}

	protected InstanceInfo getProducerServiceInstanceInfo(final EurekaClient eurekaClient) {

		final Application application = eurekaClient.getApplication(ServicesNamesConstants.PRODUCER_SERVICE_NAME);
		return application.getInstances().get(0);
	}

	protected String buildProducerServiceGreetingsUrl(final String hostname, final int port) {

		return "http://" + hostname + ":" + port + "/greetings";
	}

}
