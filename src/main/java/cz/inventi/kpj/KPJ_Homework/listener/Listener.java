package cz.inventi.kpj.KPJ_Homework.listener;


import cz.inventi.kpj.KPJ_Homework.messaging.RabbitMQConfiguration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Listener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${self.registration.message.content}")
    private String content;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        rabbitMQConfiguration.sendMessage(content);
        log.info("Self registration message sent for the service {}.", content);
    }

}
