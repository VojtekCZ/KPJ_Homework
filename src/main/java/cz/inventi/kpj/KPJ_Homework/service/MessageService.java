package cz.inventi.kpj.KPJ_Homework.service;


import cz.inventi.kpj.KPJ_Homework.database.Database;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class MessageService {
    @Autowired
    Database database;

    public List<MessageDto> getAllMessageServices() {
        List<MessageService> MessageServices = database.findAll();
        return MessageServices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MessageDto getCurrentMessageService() {
        // Implementujte získání aktuálního MessageServisu
        // a převod na MessageServiceDTO
    }

    public MessageDto getMessageServiceByName(String name) {
        Optional<MessageService> MessageServiceOptional = database.findByServiceName(name);

        if (MessageServiceOptional.isPresent()) {
            return convertToDto(MessageServiceOptional.get());
        } else {
            // Pokud mikroslužba s daným jménem neexistuje, můžete vyvolat vlastní výjimku nebo vrátit null.
            return null;
        }
    }


    public void registerMessageService(String serviceName, int port) {
        Optional<MessageService> existingMessageService = database.findByServiceName(serviceName);

        if (existingMessageService.isPresent()) {
            // Mikroslužba již existuje, můžete provést jinou logiku (např. aktualizaci portu).
        } else {
            MessageService newMessageService = new MessageService(serviceName, port);
            database.save(newMessageService);
        }
    }

    private MessageDto convertToDto(MessageService MessageService) {
        MessageDto messageDto = new MessageServiceDTO();
        messageDto.setServiceName(MessageService.getServiceName());
        messageDto.setPort(MessageService.getPort());
        return messageDto;
    }

}
