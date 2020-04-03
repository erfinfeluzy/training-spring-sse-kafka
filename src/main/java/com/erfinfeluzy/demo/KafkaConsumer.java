package com.erfinfeluzy.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class KafkaConsumer {
	
	final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	public void addEmitter(final SseEmitter emitter) {
		emitters.add(emitter);
	}

	public void removeEmitter(final SseEmitter emitter) {
		emitters.remove(emitter);
	}

	@KafkaListener(topics = "mytopic", groupId = "consumer-group-id-1")
	public void listen(@Payload String message, @Header(KafkaHeaders.OFFSET) String offset) {
		
		//process incoming message from kafka
		doNotify("Kafka Offset=" + offset + "; message=" + message);
		
	}
	
	private void doNotify(String message) {
		List<SseEmitter> deadEmitters = new ArrayList<>();
		
		emitters.forEach(emitter -> {
			try {
				//send message to frontend
				emitter
					.send(SseEmitter.event()
						.data(message));
				
			} catch (Exception e) {
				deadEmitters.add(emitter);
			}
		});
		
		emitters.removeAll(deadEmitters);
	}
	
}
