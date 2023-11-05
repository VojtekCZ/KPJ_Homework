package cz.inventi.kpj.KPJ_Homework.service;

import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;

import java.util.List;

public interface ServiceRegistrationServiceImpl {

    void registerMessageService();

    List<MessageDto> getAllMessageServices();

    MessageDto getMessageServiceByName(String name);

    MessageDto getCurrentMessageService();
}
