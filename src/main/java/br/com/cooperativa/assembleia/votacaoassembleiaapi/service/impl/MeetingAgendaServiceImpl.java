package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.MeetingAgendaConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.MeetingAgendaRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingAgendaServiceImpl implements MeetingAgendaService {

    private MeetingAgendaRepository meetingAgendaRepository;
    private MeetingAgendaConverter meetingAgendaConverter;

    public MeetingAgendaServiceImpl(MeetingAgendaRepository meetingAgendaRepository, MeetingAgendaConverter meetingAgendaConverter) {
        this.meetingAgendaRepository = meetingAgendaRepository;
        this.meetingAgendaConverter = meetingAgendaConverter;
    }

    @Override
    public List<MeetingAgendaDto> getAll() {
        return meetingAgendaConverter.listDtoFromListEntity(meetingAgendaRepository.findAll());
    }
}
