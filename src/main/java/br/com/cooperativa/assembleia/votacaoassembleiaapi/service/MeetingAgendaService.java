package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaStartSessionForm;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MeetingAgendaService {
    List<MeetingAgendaDto> getAll();

    MeetingAgendaDto create(@NotNull @Valid MeetingAgendaForm meetingAgendaForm);

    MeetingAgendaDto findOne(@NotBlank String id);

    MeetingAgendaDto startSession(
            @NotNull @Valid MeetingAgendaStartSessionForm meetingAgendaStartSessionForm,
            @NotBlank String id
    );
}
