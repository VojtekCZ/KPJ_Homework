package cz.inventi.kpj.KPJ_Homework.controller;

import cz.inventi.kpj.KPJ_Homework.database.MessageResponse;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import cz.inventi.kpj.KPJ_Homework.service.ServiceRegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Controller.CONTROLLER_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class Controller {

    public static final String CONTROLLER_ROOT = "/services";


    private final ServiceRegistrationServiceImpl messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAllMessageServices() {
        return ResponseEntity.ok(messageService.getAllMessageServices());
    }

    @GetMapping("/current")
    public ResponseEntity<MessageDto> getCurrentMessageService() {
        return ResponseEntity.ok(messageService.getCurrentMessageService());
    }

    @GetMapping("/{name}")
    public ResponseEntity<MessageDto> getMessageServiceByName(@PathVariable String name) {
        return ResponseEntity.ok(messageService.getMessageServiceByName(name));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MessageResponse> registerMessageService() {
        messageService.registerMessageService();
        return ResponseEntity.ok(new MessageResponse("Registration message sent successfully."));
    }

}
