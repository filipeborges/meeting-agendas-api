package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaMessageResultDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingAgendaConverter {
    private final VoteConverter voteConverter;

    public MeetingAgendaConverter(VoteConverter voteConverter) {
        this.voteConverter = voteConverter;
    }

    public MeetingAgendaDto dtoFromEntity(MeetingAgenda meetingAgenda) {
        if (meetingAgenda == null) return null;
        return new MeetingAgendaDto(
                meetingAgenda.getId(),
                meetingAgenda.getName(),
                meetingAgenda.getDescription(),
                meetingAgenda.getSessionExpireIn(),
                meetingAgenda.getAcceptedVotes(),
                meetingAgenda.getRejectedVotes(),
                meetingAgenda.getResult(),
                voteConverter.listDtoFromListEntity(meetingAgenda.getVotes())
        );
    }

    public List<MeetingAgendaDto> listDtoFromListEntity(List<MeetingAgenda> meetingAgenda) {
        if (meetingAgenda == null) return null;
        return meetingAgenda
                .stream()
                .map(this::dtoFromEntity)
                .collect(Collectors.toList());
    }

    public MeetingAgenda entityFromForm(MeetingAgendaForm meetingAgendaForm) {
        if (meetingAgendaForm == null) return null;
        return new MeetingAgenda(
                meetingAgendaForm.getName(),
                meetingAgendaForm.getDescription()
        );
    }

    public MeetingAgendaMessageResultDto messageDtoFromEntity(MeetingAgenda meetingAgenda) {
        if (meetingAgenda == null) return null;
        return new MeetingAgendaMessageResultDto(
                meetingAgenda.getName(),
                meetingAgenda.getAcceptedVotes(),
                meetingAgenda.getRejectedVotes(),
                meetingAgenda.getResult()
        );
    }
}
