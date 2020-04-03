package com.erfinfeluzy.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class StreamController {
	
	@Autowired
	KafkaConsumer kafkaConsumer;

	@GetMapping("/stream")
	SseEmitter  stream() throws IOException {

		final SseEmitter emitter = new SseEmitter();
		kafkaConsumer.addEmitter(emitter);
		
		emitter.onCompletion(() -> kafkaConsumer.removeEmitter(emitter));
		emitter.onTimeout(() -> kafkaConsumer.removeEmitter(emitter));
		
		return emitter;

	}

}
