package cz.inventi.kpj.KPJ_Homework.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private int id;
    @NotNull
    private String serviceName;
    @NotNull
    private String port;
    @NotNull
    private LocalDateTime registerTime;
}


