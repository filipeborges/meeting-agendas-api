package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.MeetingAgendaConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.MeetingAgendaRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class MeetingAgendaServiceImpl implements MeetingAgendaService {

    private static final String RESOURCE_NAME = "meeting-agenda";

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

    @Override
    public MeetingAgendaDto create(@NotNull @Valid MeetingAgendaForm meetingAgendaForm) {
        return meetingAgendaConverter.dtoFromEntity(
                meetingAgendaRepository.save(meetingAgendaConverter.entityFromForm(meetingAgendaForm))
        );
    }

    @Override
    public MeetingAgendaDto findOne(@NotBlank String id) {
        MeetingAgenda meetingAgenda = meetingAgendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
        return meetingAgendaConverter.dtoFromEntity(meetingAgenda);
    }
}
