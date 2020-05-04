package br.com.cooperativa.assembleia.votacaoassembleiaapi.scheduler;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CloseVotingSessionScheduler {

    private MeetingAgendaService meetingAgendaService;

    public CloseVotingSessionScheduler(MeetingAgendaService meetingAgendaService) {
        this.meetingAgendaService = meetingAgendaService;
    }

    @Scheduled(fixedDelayString = "${app.scheduler.close-voting-session.execution-interval-ms}")
    public void closeVotingSession() {
        meetingAgendaService.closePendingVotingSession();
    }

}
