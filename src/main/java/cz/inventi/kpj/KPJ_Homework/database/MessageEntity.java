package cz.inventi.kpj.KPJ_Homework.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue
    private int id;
    private String serviceName;
    private String port;
    private LocalDateTime registerTime;



}
