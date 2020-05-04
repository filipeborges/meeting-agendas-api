package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteForm;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface VoteService {
    List<VoteDto> getAll(@NotBlank String meetingAgendaId);

    VoteDto update(@NotNull @Valid VoteForm voteForm, @NotBlank String meetingAgendaId, @NotBlank String associateId);

    VoteDto getVoteFromAssociate(@NotBlank String meetingAgendaId, @NotBlank String associateId);
}
