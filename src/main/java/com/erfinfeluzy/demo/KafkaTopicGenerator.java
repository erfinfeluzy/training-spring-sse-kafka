package com.erfinfeluzy.demo;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class KafkaTopicGenerator {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void doNotify() throws IOException {
		
		//randomly generate kafka message to topic:mytopic every 5 seconds
		kafkaTemplate.send("mytopic", "Data tanggal : " + new Date () + "; id : " + UUID.randomUUID() );
	}
	
}
