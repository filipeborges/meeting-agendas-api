package br.com.cooperativa.assembleia.votacaoassembleiaapi.scheduler;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.MeetingAgendaConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CloseVotingSessionScheduler {

    @Value("${app.rabbitmq.queue-name}")
    private String queueName;

    private final MeetingAgendaService meetingAgendaService;
    private final RabbitTemplate rabbitTemplate;
    private final MeetingAgendaConverter meetingAgendaConverter;

    public CloseVotingSessionScheduler(
            MeetingAgendaService meetingAgendaService,
            RabbitTemplate rabbitTemplate,
            MeetingAgendaConverter meetingAgendaConverter
    ) {
        this.meetingAgendaService = meetingAgendaService;
        this.rabbitTemplate = rabbitTemplate;
        this.meetingAgendaConverter = meetingAgendaConverter;
    }

    @Scheduled(fixedDelayString = "${app.scheduler.close-voting-session.execution-interval-ms}")
    public void closeVotingSession() {
        List<MeetingAgenda> meetingAgendas = meetingAgendaService.closePendingVotingSession();
        meetingAgendas.forEach(meetingAgenda -> {
            rabbitTemplate.convertAndSend(
                    queueName,
                    meetingAgendaConverter.messageDtoFromEntity(meetingAgenda)
            );
        });
    }

}
