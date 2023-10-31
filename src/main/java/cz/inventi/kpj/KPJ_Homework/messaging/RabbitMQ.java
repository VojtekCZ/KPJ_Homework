package cz.inventi.kpj.KPJ_Homework.messaging;

import cz.inventi.kpj.KPJ_Homework.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQ {
    @Autowired
    private MessageService messageService;

    @RabbitListener(queues = "kpj.vojtechleskovsky")
    public void processMessage(String message) {
        String[] parts = message.split(":");
        String serviceName = parts[0];
        int port = Integer.parseInt(parts[1]);

        messageService.processMessage(serviceName, port);
    }
}
