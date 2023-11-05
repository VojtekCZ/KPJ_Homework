package cz.inventi.kpj.KPJ_Homework.service;

import cz.inventi.kpj.KPJ_Homework.database.Database;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import cz.inventi.kpj.KPJ_Homework.mapper.MessageMapper;
import cz.inventi.kpj.KPJ_Homework.messaging.RabbitMQConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService implements ServiceRegistrationServiceImpl{

    @Value("${self.registration.message.content}")
    private String content;

    private final Database database;
    private final MessageMapper messageMapper;
    private final RabbitMQConfiguration rabbitMQConfiguration;

    @Override
    public void registerMessageService() {
        rabbitMQConfiguration.sendMessage(content);

    }
    @Override
    public List<MessageDto> getAllMessageServices() {
        return database.findAll().stream().map(messageMapper::messageEntityToMessageDto).collect(Collectors.toList());
    }

    @Override
    public MessageDto getCurrentMessageService() {
        return getMessageServiceByName(content);
    }

    @Override
    public MessageDto getMessageServiceByName(String name) {
        return messageMapper.messageEntityToMessageDto(database.findByServiceName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public static String getMessageName(String serviceMessage) {
        return StringUtils.substringBefore(serviceMessage, ";");
    }

    public static String getMessagePort(String serviceMessage) {
        return StringUtils.substringAfter(serviceMessage, ";");
    }







}
