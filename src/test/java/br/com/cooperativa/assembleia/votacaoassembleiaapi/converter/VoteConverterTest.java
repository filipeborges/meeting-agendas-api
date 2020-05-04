package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Vote;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.enums.VoteResultEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VoteConverterTest {

    private VoteConverter voteConverter;

    @BeforeEach
    public void setup() {
        voteConverter = new VoteConverter();
    }

    @Test
    public void entityFromFormAndAssociateIdSuccess() {
        VoteForm voteForm = new VoteForm(VoteResultEnum.accept);
        Vote vote = voteConverter.entityFromFormAndAssociateId(voteForm, "1234");
        assertNotNull(vote, "Vote must be not null");
        assertEquals(voteForm.getVote().name(), vote.getValue(), "Votes should be equal");
        assertEquals("1234", vote.getAssociateId(), "Associate id should be equal");
    }

    @Test
    public void entityFromFormAndAssociateIdNullParam() {
        Vote vote = voteConverter.entityFromFormAndAssociateId(null, "");
        assertNull(vote, "Meeting agenda must be null");
    }

    @Test
    public void dtoFromEntitySuccess() {
        Vote vote = new Vote("1234", "accept");
        VoteDto voteDto = voteConverter.dtoFromEntity(vote);
        assertNotNull(voteDto);
        assertEquals(vote.getAssociateId(), voteDto.getAssociateId(), "Associate id should be equal");
        assertEquals(vote.getValue(), voteDto.getVote(), "Vote should be equal");
    }

    @Test
    public void dtoFromEntityNullParam() {
        VoteDto voteDto = voteConverter.dtoFromEntity(null);
        assertNull(voteDto, "voteDto should be null");
    }

    @Test
    public void listDtoFromListEntitySuccess() {
        Vote vote1 = new Vote("1234", "accept");
        Vote vote2 = new Vote("6789", "reject");
        List<Vote> listEntity = List.of(vote1, vote2);
        List<VoteDto> listDto = voteConverter.listDtoFromListEntity(listEntity);

        assertNotNull(listDto, "List Dto should be not null");
        assertEquals(2, listDto.size(), "List Dto size should be 2");
        assertEquals(vote1.getAssociateId(), listDto.get(0).getAssociateId(), "Associate id should be equal");
        assertEquals(vote1.getValue(), listDto.get(0).getVote(), "Vote should be equal");
        assertEquals(vote2.getAssociateId(), listDto.get(1).getAssociateId(), "Associate id should be equal");
        assertEquals(vote2.getValue(), listDto.get(1).getVote(), "Vote should be equal");
    }

    @Test
    public void listDtoFromListEntityNullParam() {
        List<VoteDto> listDto = voteConverter.listDtoFromListEntity(null);
        assertNull(listDto, "List dto should be null");
    }
}
