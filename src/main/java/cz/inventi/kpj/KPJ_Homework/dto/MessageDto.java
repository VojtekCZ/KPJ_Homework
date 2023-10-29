package cz.inventi.kpj.KPJ_Homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
public class MessageDto {
    private String serviceName;
    private int port;

}


