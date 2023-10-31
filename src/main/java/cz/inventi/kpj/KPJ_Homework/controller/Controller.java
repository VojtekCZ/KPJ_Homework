package cz.inventi.kpj.KPJ_Homework.controller;

import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import cz.inventi.kpj.KPJ_Homework.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class Controller {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
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

    @PostMapping("/register")
    public void registerMessage(@RequestParam String serviceName, @RequestParam int port) {
        messageService.registerMessageService(serviceName, port);
    }
}
