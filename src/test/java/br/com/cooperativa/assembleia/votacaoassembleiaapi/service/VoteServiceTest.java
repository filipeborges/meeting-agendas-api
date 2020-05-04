package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.VoteConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Vote;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.MeetingAgendaRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl.VoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VoteServiceTest {
    private MeetingAgendaService meetingAgendaServiceMock;
    private VoteConverter voteConverterMock;
    private AssociateService associateServiceMock;
    private MeetingAgendaRepository meetingAgendaRepositoryMock;

    private VoteService voteService;

    @BeforeEach
    public void setup() {
        meetingAgendaServiceMock = Mockito.mock(MeetingAgendaService.class);
        voteConverterMock = Mockito.mock(VoteConverter.class);
        associateServiceMock = Mockito.mock(AssociateService.class);
        meetingAgendaRepositoryMock = Mockito.mock(MeetingAgendaRepository.class);
        voteService = new VoteServiceImpl(
                meetingAgendaServiceMock,
                voteConverterMock,
                associateServiceMock,
                meetingAgendaRepositoryMock
        );
    }

    @Test
    public void getVoteFromAssociateSuccess() {
        when(meetingAgendaRepositoryMock.findVoteByMeetingAgendaIdAndAssociateId(any(), any()))
                .thenReturn(Optional.of(buildMeetingAgenda()));
        assertDoesNotThrow(() -> voteService.getVoteFromAssociate("1234", "6789"));
    }

    @Test
    public void getVoteFromAssociateFail() {
        MeetingAgenda meetingAgenda = new MeetingAgenda("name", "description");
        when(meetingAgendaRepositoryMock.findVoteByMeetingAgendaIdAndAssociateId(any(), any()))
                .thenReturn(Optional.of(meetingAgenda));
        assertThrows(
                ResourceNotFoundException.class,
                () -> voteService.getVoteFromAssociate("1234", "6789")
        );
    }

    private MeetingAgenda buildMeetingAgenda() {
        return new MeetingAgenda(
                "name",
                "description",
                System.currentTimeMillis() + 30000L,
                1L,
                0L,
                "accepted",
                List.of(new Vote("1234", "accept"))
        );
    }
}
