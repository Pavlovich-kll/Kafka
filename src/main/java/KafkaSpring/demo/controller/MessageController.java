package KafkaSpring.demo.controller;

import KafkaSpring.demo.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final KafkaTemplate<Long, UserDto> kafkaTemplate;

    public MessageController(KafkaTemplate<Long, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendMsg(@RequestParam Long msgId,
                        @RequestBody UserDto userDto) {
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("message", msgId, userDto);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
