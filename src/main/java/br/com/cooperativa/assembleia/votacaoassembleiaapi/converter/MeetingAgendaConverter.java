package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
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
                meetingAgenda.getName(),
                meetingAgenda.getDescription(),
                meetingAgenda.getSessionStartedIn(),
                meetingAgenda.getSessionIntervalDuration(),
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
}
