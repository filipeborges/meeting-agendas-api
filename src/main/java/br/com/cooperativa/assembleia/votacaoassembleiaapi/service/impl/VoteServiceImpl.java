package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.VoteConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Vote;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.VotingSessionExpiredException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.MeetingAgendaRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.VoteService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final MeetingAgendaService meetingAgendaService;
    private final VoteConverter voteConverter;
    private final AssociateService associateService;
    private final MeetingAgendaRepository meetingAgendaRepository;

    public VoteServiceImpl(
            MeetingAgendaService meetingAgendaService,
            VoteConverter voteConverter,
            AssociateService associateService,
            MeetingAgendaRepository meetingAgendaRepository
    ) {
        this.meetingAgendaService = meetingAgendaService;
        this.voteConverter = voteConverter;
        this.associateService = associateService;
        this.meetingAgendaRepository = meetingAgendaRepository;
    }

    @Override
    public List<VoteDto> getAll(@NotBlank String meetingAgendaId) {
        return meetingAgendaService.findOne(meetingAgendaId).getVotes();
    }

    @Override
    public VoteDto update(
            @NotNull VoteForm voteForm,
            @NotBlank String meetingAgendaId,
            @NotBlank String associateId
    ) {
        MeetingAgenda meetingAgenda = meetingAgendaService.findOneEntity(meetingAgendaId);
        verifyIfVotingSessionIsOpen(meetingAgenda);
        verifyIfAssociateIsAllowedToVote(associateId);
        Vote newVote = voteConverter.entityFromFormAndAssociateId(voteForm, associateId);
        updateVote(meetingAgenda.getVotes(), newVote);
        meetingAgendaService.saveEntity(meetingAgenda);
        return voteConverter.dtoFromEntity(newVote);
    }

    @Override
    public VoteDto getVoteFromAssociate(@NotBlank String meetingAgendaId, @NotBlank String associateId) {
        MeetingAgenda meetingAgenda = meetingAgendaRepository.findVoteByMeetingAgendaIdAndAssociateId(
                meetingAgendaId, associateId
        ).orElseThrow(() -> new ResourceNotFoundException("meeting-agenda", meetingAgendaId));

        final boolean emptyVotes = meetingAgenda.getVotes() == null || meetingAgenda.getVotes().isEmpty();
        if (emptyVotes) throw new ResourceNotFoundException("Associate vote not found");

        return voteConverter.dtoFromEntity(meetingAgenda.getVotes().get(0));
    }

    private void verifyIfVotingSessionIsOpen(MeetingAgenda meetingAgenda) {
        if (!meetingAgenda.isSessionOpen())
            throw new VotingSessionExpiredException(
                    meetingAgenda.getSessionStartedIn(), meetingAgenda.getSessionIntervalDuration()
            );
    }

    private void verifyIfAssociateIsAllowedToVote(String associateId) {
        associateService.verifyIfAssociateExists(associateId);
        associateService.verifyIfAssociateIsAbleToVote(associateId);
    }

    private void updateVote(List<Vote> existingVotes, Vote vote) {
        Optional<Vote> matchedVote = existingVotes
                .stream()
                .filter(exitingVote -> exitingVote.getAssociateId().equals(vote.getAssociateId()))
                .findFirst();

        if (matchedVote.isEmpty()) {
            existingVotes.add(vote);
        } else {
            matchedVote.get().setValue(vote.getValue());
        }
    }
}
