package cz.inventi.kpj.KPJ_Homework.messaging;

import cz.inventi.kpj.KPJ_Homework.database.Database;
import cz.inventi.kpj.KPJ_Homework.database.MessageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static cz.inventi.kpj.KPJ_Homework.service.MessageService.getMessageName;
import static cz.inventi.kpj.KPJ_Homework.service.MessageService.getMessagePort;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConfiguration implements MessagingService {

    private static final String QUEUE_NAME_PROPERTY_NAME = "${rabbitmq.queue..json.name}";
    private static final String FANOUT_NAME_PROPERTY_NAME = "${rabbitmq.exchange.name}";
    private static final String THIS_SERVICE_REGISTRATION_MESSAGE_PROPERTY_NAME = "${self.registration.message.content}";

    @Value(QUEUE_NAME_PROPERTY_NAME)
    private String queue;

    @Value(FANOUT_NAME_PROPERTY_NAME)
    private String exchange;

    @Value(THIS_SERVICE_REGISTRATION_MESSAGE_PROPERTY_NAME)
    private String content;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    Database database;

    @Bean
    Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(exchange, true, false);
    }

    @Bean
    public Binding listenerBinding(
            @Qualifier("queue") Queue queue,
            @Qualifier("exchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    @Override
    @RabbitListener(bindings = @QueueBinding(
            value = @org.springframework.amqp.rabbit.annotation.Queue(
                    name = QUEUE_NAME_PROPERTY_NAME),
            exchange = @Exchange(
                    name = FANOUT_NAME_PROPERTY_NAME,
                    type = ExchangeTypes.FANOUT)))

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


    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, "", message);
    }

    private boolean serviceAlreadyExists(String message) {
        return database.findAll().stream().anyMatch(messageEntity -> messageEntity.getServiceName().equals(getMessageName(message)));
    }
}
