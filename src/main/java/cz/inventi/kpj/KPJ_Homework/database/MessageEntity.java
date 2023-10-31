package cz.inventi.kpj.KPJ_Homework.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class MessageEntity {

    @Id
    private String serviceName;
    private int port;


}
