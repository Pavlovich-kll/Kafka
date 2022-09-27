package KafkaSpring.demo.controller;

import KafkaSpring.demo.dto.MessageDto;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendOrder(@RequestBody MessageDto messageDto) {
        kafkaTemplate.send("message", messageDto.getMsgId(), messageDto.getMsg());
    }
}
