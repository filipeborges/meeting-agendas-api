package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MeetingAgendaStartSessionForm {
    @NotNull
    @Positive
    @Max(60L)
    private Long sessionDurationMin;

    public MeetingAgendaStartSessionForm() {
        this.sessionDurationMin = 1L;
    }

    public MeetingAgendaStartSessionForm(@NotNull @Positive @Max(60L) Long sessionDurationMin) {
        this.sessionDurationMin = sessionDurationMin;
    }

    public Long getSessionDurationMin() {
        return sessionDurationMin;
    }

    public void setSessionDurationMin(Long sessionDurationMin) {
        this.sessionDurationMin = sessionDurationMin;
    }

    @Override
    public String toString() {
        return "MeetingAgendaStartSessionForm{" +
                "sessionDurationMin=" + sessionDurationMin +
                '}';
    }
}
