package cz.inventi.kpj.KPJ_Homework.service;


import cz.inventi.kpj.KPJ_Homework.database.Database;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class MessageService {
    @Autowired
    Database database;

    public List<MessageDto> getAllMessageservices() {
        List<Messageservice> Messageservices = database.findAll();
        return Messageservices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MessageDto getCurrentMessageservice() {
        // Implementujte získání aktuálního Messageservisu
        // a převod na MessageserviceDTO
    }

    public MessageDto getMessageserviceByName(String name) {
        Optional<Messageservice> MessageserviceOptional = database.findByServiceName(name);

        if (MessageserviceOptional.isPresent()) {
            return convertToDto(MessageserviceOptional.get());
        } else {
            // Pokud mikroslužba s daným jménem neexistuje, můžete vyvolat vlastní výjimku nebo vrátit null.
            return null;
        }
    }


    public void registerMessageservice(String serviceName, int port) {
        Optional<Messageservice> existingMessageservice = database.findByServiceName(serviceName);

        if (existingMessageservice.isPresent()) {
            // Mikroslužba již existuje, můžete provést jinou logiku (např. aktualizaci portu).
        } else {
            Messageservice newMessageservice = new Messageservice(serviceName, port);
            database.save(newMessageservice);
        }
    }

    private MessageDto convertToDto(Messageservice Messageservice) {
        MessageDto messageDto = new MessageserviceDTO();
        messageDto.setServiceName(Messageservice.getServiceName());
        messageDto.setPort(Messageservice.getPort());
        return messageDto;
    }

}
