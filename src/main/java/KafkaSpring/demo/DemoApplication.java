package KafkaSpring.demo;

import KafkaSpring.demo.config.KafkaProducerConfig;
import KafkaSpring.demo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@EnableKafka
@SpringBootApplication
public class DemoApplication {

	@KafkaListener(topics="message")
	public void msgListener(ConsumerRecord<Long, UserDto> record){
		log.info("Partition {}.", record.partition());
		log.info("Key {}.", record.key());
		log.info("Value {}.", record.value());
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(
				DemoApplication.class,
				KafkaProducerConfig.class
		)
				.build(args)
				.run();
	}

}
