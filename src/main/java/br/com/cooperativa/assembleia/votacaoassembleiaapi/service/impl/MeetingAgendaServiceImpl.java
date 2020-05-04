package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.MeetingAgendaConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaStartSessionForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.MeetingAgendaRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class MeetingAgendaServiceImpl implements MeetingAgendaService {

    private static final String RESOURCE_NAME = "meeting-agenda";

    private final MeetingAgendaRepository meetingAgendaRepository;
    private final MeetingAgendaConverter meetingAgendaConverter;

    public MeetingAgendaServiceImpl(MeetingAgendaRepository meetingAgendaRepository,
                                    MeetingAgendaConverter meetingAgendaConverter) {
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
        return meetingAgendaConverter.dtoFromEntity(findOneEntity(id));
    }

    @Override
    public MeetingAgendaDto startSession(
            @NotNull @Valid MeetingAgendaStartSessionForm meetingAgendaStartSessionForm,
            @NotBlank String id
    ) {
        MeetingAgenda meetingAgenda = meetingAgendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
        meetingAgenda.setSessionExpireIn(
                new Date().getTime() + calculateSessionIntervalDuration(meetingAgendaStartSessionForm.getSessionDurationMin())
        );
        return meetingAgendaConverter.dtoFromEntity(meetingAgendaRepository.save(meetingAgenda));
    }

    @Override
    public MeetingAgenda findOneEntity(@NotBlank String id) {
        return meetingAgendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
    }

    @Override
    public MeetingAgenda saveEntity(@NotNull MeetingAgenda meetingAgenda) {
        return meetingAgendaRepository.save(meetingAgenda);
    }

    @Override
    public List<MeetingAgenda> closePendingVotingSession() {
        List<MeetingAgenda> pendingMeetingAgendas = meetingAgendaRepository.findAllVotingSessionExpired(
                new Date().getTime()
        );
        updateMeetingAgendaVotingResult(pendingMeetingAgendas);
        return meetingAgendaRepository.saveAll(pendingMeetingAgendas);
    }

    private void updateMeetingAgendaVotingResult(@NotNull List<MeetingAgenda> pendingMeetingAgendas) {
        pendingMeetingAgendas
                .stream()
                .filter(meetAgenda -> StringUtils.isEmpty(meetAgenda.getResult()))
                .forEach(meetAgenda -> {
                    meetAgenda.updateNumberOfVotes();
                    meetAgenda.updateFinalResult();
                });
    }

    private Long calculateSessionIntervalDuration(@NotNull Long sessionDurationMin) {
        return sessionDurationMin * 60L * 1000L;
    }
}
