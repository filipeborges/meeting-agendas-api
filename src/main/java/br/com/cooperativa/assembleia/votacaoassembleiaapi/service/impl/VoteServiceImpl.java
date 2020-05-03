package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.VoteConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.VoteService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    private final MeetingAgendaService meetingAgendaService;
    private final VoteConverter voteConverter;

    public VoteServiceImpl(MeetingAgendaService meetingAgendaService, VoteConverter voteConverter) {
        this.meetingAgendaService = meetingAgendaService;
        this.voteConverter = voteConverter;
    }

    @Override
    public List<VoteDto> getAll(@NotBlank String meetingAgendaId) {
        return meetingAgendaService.findOne(meetingAgendaId).getVotes();
    }
}
