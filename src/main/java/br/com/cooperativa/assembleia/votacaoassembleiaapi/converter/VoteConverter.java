package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteConverter {

    public VoteDto dtoFromEntity(Vote vote) {
        if (vote == null) return null;
        return new VoteDto(vote.getAssociateId(), vote.getValue());
    }

    public List<VoteDto> listDtoFromListEntity(List<Vote> votes) {
        if (votes == null) return null;
        return votes
                .stream()
                .map(vote -> new VoteDto(vote.getAssociateId(), vote.getValue()))
                .collect(Collectors.toList());
    }
}
