package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;

import java.util.List;

public interface MeetingAgendaService {
    List<MeetingAgendaDto> getAll();
}
