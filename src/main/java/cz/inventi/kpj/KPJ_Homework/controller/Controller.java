package cz.inventi.kpj.KPJ_Homework.controller;

import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import cz.inventi.kpj.KPJ_Homework.producer.Producer;
import cz.inventi.kpj.KPJ_Homework.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    public Controller(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    private Producer producer;

    @Autowired
    private MessageService messageService;

    @GetMapping("/services")
    public List<MessageDto> getAllMessageServices() {
        return messageService.getAllMessageServices();
    }

    @GetMapping("/current")
    public MessageDto getCurrentMessageService() {
        return messageService.getCurrentMessageService();
    }

    @GetMapping("/{name}")
    public MessageDto getMessageServiceByName(@PathVariable String name) {
        return messageService.getMessageServiceByName(name);
    }

    @GetMapping("/register")
    public ResponseEntity<String> registerMessage(@RequestParam String message) {
        messageService.registerMessageService(message);
        producer.processMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

}
