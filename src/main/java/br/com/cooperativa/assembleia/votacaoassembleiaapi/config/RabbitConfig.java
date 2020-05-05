package br.com.cooperativa.assembleia.votacaoassembleiaapi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.rabbitmq.queue-name}")
    private String queueName;

    @Bean
    public Queue meetingAgendaResultQueue() {
        return new Queue(queueName, false);
    }

}
