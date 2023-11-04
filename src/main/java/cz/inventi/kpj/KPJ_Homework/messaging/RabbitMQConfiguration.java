package cz.inventi.kpj.KPJ_Homework.messaging;

import cz.inventi.kpj.KPJ_Homework.database.Database;
import cz.inventi.kpj.KPJ_Homework.database.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static cz.inventi.kpj.KPJ_Homework.service.MessageService.getMessageName;
import static cz.inventi.kpj.KPJ_Homework.service.MessageService.getMessagePort;

@Configuration
@Slf4j
public class RabbitMQConfiguration implements MessagingService{

    @Value("${rabbitmq.queue.json.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${self.registration.message.content}")
    private String content;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    Database database;

    @Bean
    Queue queue()  {
        return new Queue (queue);
    }

    @Bean
    public FanoutExchange exchange() { return new FanoutExchange(exchange); }

    @Bean
    public Binding listenerBinding(
            @Qualifier("kpjQueue") Queue queue,
            @Qualifier("kpjFanout") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, "", message);
    }
    @Override
    public void receiveMessage(String message) {
        log.info("Message received: {}", message);
        if (!serviceAlreadyExists(message)) {
            database.save(new MessageEntity(0, getMessageName(message), getMessagePort(message), LocalDateTime.now()));
            sendMessage(content);
            log.info("Self registration message sent for this service {}.", content);
        } else {
            log.info("The service [{}] is already registered.", message);
        }
    }
    private boolean serviceAlreadyExists(String message) {
        return database.findAll().stream().anyMatch(serviceEntity -> serviceEntity.getServiceName().equals(getMessageName(message)));
    }

}
