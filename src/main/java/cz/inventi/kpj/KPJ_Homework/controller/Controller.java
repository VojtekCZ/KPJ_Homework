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
    public List<MessageDto> getAllMessageservices() {
        return messageService.getAllMessageservices();
    }

    @GetMapping("/current")
    public MessageDto getCurrentMessageservice() {
        return messageService.getCurrentMessageservice();
    }

    @GetMapping("/{name}")
    public MessageDto getMessageserviceByName(@PathVariable String name) {
        return messageService.getMessageserviceByName(name);
    }

    @PostMapping("/register")
    public void registerMicroservice() {
        messageService.registerMessageservice();
    }

}
