package br.com.cooperativa.assembleia.votacaoassembleiaapi.scheduler;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaMessageResultDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MeetingAgendaMessageResultListener {

    @RabbitListener(queues = "${app.rabbitmq.queue-name}")
    public void resultListener(MeetingAgendaMessageResultDto resultDto) {
        System.out.println("============= Meeting Agenda result received ==============");
        System.out.println(resultDto);
    }

}
