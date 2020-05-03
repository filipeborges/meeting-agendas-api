package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface VoteService {
    List<VoteDto> getAll(@NotBlank String meetingAgendaId);
}
