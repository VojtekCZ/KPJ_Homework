package cz.inventi.kpj.KPJ_Homework.controller;

import cz.inventi.kpj.KPJ_Homework.database.MessageResponse;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import cz.inventi.kpj.KPJ_Homework.producer.Producer;
import cz.inventi.kpj.KPJ_Homework.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
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

    @PostMapping(value = "/register")
    public ResponseEntity<MessageResponse> registerService() {
        messageService.registerMessageService();
        return ResponseEntity.ok(new MessageResponse("Registration message sent successfully."));
    }

}
