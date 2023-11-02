package cz.inventi.kpj.KPJ_Homework.producer;

import cz.inventi.kpj.KPJ_Homework.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void processMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, message);


    }



}
