package cz.inventi.kpj.KPJ_Homework.service;


import cz.inventi.kpj.KPJ_Homework.database.Database;
import cz.inventi.kpj.KPJ_Homework.database.MessageEntity;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    Database database;

    public List<MessageDto> getAllMessageServices() {
        List<MessageEntity> messageEntities = database.findAll();
        return messageEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MessageDto getCurrentMessageService() {
        //dopsat
        return null;
    }

    public MessageDto getMessageServiceByName(String name) {
        Optional<MessageEntity> messageEntityOptional = database.findByServiceName(name);

        if (messageEntityOptional.isPresent()) {
            return convertToDto(messageEntityOptional.get());
        } else {
            return null;
        }
    }


    public void registerMessageService(String message) {
        Optional<MessageEntity> existingMessageService = database.findByServiceName(message);
        String[] parts = message.split(";");
        String serviceName = parts[0];
        int port = Integer.parseInt(parts[1]);

        if (existingMessageService.isPresent()) {
            System.out.println("Microservice " + message + " is already registered.");
        } else {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setServiceName(serviceName);
            messageEntity.setPort(port);
            database.save(messageEntity);
            System.out.println("Microservice " + serviceName + " registered successfully.");
        }
    }


    private MessageDto convertToDto(MessageEntity messageEntity) {
        MessageDto messageDto = new MessageDto();
        messageDto.setServiceName(messageEntity.getServiceName());
        messageDto.setPort(messageEntity.getPort());
        return messageDto;
    }

}
