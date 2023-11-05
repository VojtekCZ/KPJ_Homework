package cz.inventi.kpj.KPJ_Homework.mapper;

import cz.inventi.kpj.KPJ_Homework.database.MessageEntity;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto messageEntityToMessageDto(MessageEntity messageEntity);

    @InheritInverseConfiguration
    MessageEntity messageDtoToMessageEntity(MessageDto messageDto);
}
