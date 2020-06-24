package com.erfinfeluzy.demo;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic}")
	private String kafkaTopic;
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void doNotify() throws IOException {
		
		//randomly generate kafka message to topic:mytopic every 5 seconds
		kafkaTemplate.send(kafkaTopic, "Data tanggal : " + new Date () + "; id : " + UUID.randomUUID() );
	}
	
}
