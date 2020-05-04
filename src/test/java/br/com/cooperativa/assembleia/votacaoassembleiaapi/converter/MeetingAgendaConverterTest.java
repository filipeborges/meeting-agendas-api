package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MeetingAgendaConverterTest {

    private MeetingAgendaConverter meetingAgendaConverter;

    @BeforeEach
    public void setup() {
        meetingAgendaConverter = new MeetingAgendaConverter(Mockito.mock(VoteConverter.class));
    }

    @Test
    public void entityFromFormSuccess() {
        MeetingAgendaForm meetingAgendaForm = new MeetingAgendaForm("Name", "Description");
        MeetingAgenda meetingAgenda = meetingAgendaConverter.entityFromForm(meetingAgendaForm);
        assertNotNull(meetingAgenda, "Meeting agenda must be not null");
        assertEquals(meetingAgendaForm.getName(), meetingAgenda.getName(), "Names should be equal");
        assertEquals(meetingAgendaForm.getDescription(), meetingAgenda.getDescription(), "Description should be equal");
    }

    @Test
    public void entityFromFormNullParam() {
        MeetingAgenda meetingAgenda = meetingAgendaConverter.entityFromForm(null);
        assertNull(meetingAgenda, "Meeting agenda must be null");
    }

    @Test
    public void dtoFromEntitySuccess() {
        MeetingAgenda meetingAgenda = new MeetingAgenda(
                "Name",
                "Description",
                System.currentTimeMillis() + 400000L,
                0L,
                0L,
                "tied",
                Collections.emptyList()
        );
        MeetingAgendaDto meetingAgendaDto = meetingAgendaConverter.dtoFromEntity(meetingAgenda);

        assertNotNull(meetingAgendaDto);
        assertEquals(meetingAgenda.getName(), meetingAgendaDto.getName(), "Names should be equal");
        assertEquals(meetingAgenda.getDescription(), meetingAgendaDto.getDescription(), "Description should be equal");
        assertEquals(meetingAgenda.getSessionExpireIn(), meetingAgendaDto.getSessionExpireIn(), "SessionExpireIn should be equal");
        assertEquals(meetingAgenda.getAcceptedVotes(), meetingAgendaDto.getAcceptedVotes(), "Accepted votes should be equal");
        assertEquals(meetingAgenda.getRejectedVotes(), meetingAgendaDto.getRejectedVotes(), "Rejected votes should be equal");
        assertEquals(meetingAgenda.getResult(), meetingAgendaDto.getResult(), "Result should be equal");
    }

    @Test
    public void dtoFromEntityNullParam() {
        MeetingAgendaDto meetingAgendaDto = meetingAgendaConverter.dtoFromEntity(null);
        assertNull(meetingAgendaDto, "meetingAgendaDto should be null");
    }

    @Test
    public void listDtoFromListEntitySuccess() {
        MeetingAgenda meetingAgenda1 = new MeetingAgenda("Name 1", "Description 1");
        MeetingAgenda meetingAgenda2 = new MeetingAgenda("Name 2", "Description 2");
        List<MeetingAgenda> listEntity = List.of(meetingAgenda1, meetingAgenda2);
        List<MeetingAgendaDto> listDto = meetingAgendaConverter.listDtoFromListEntity(listEntity);

        assertNotNull(listDto, "List Dto should be not null");
        assertEquals(2, listDto.size(), "List Dto size should be 2");
        assertEquals(meetingAgenda1.getName(), listDto.get(0).getName(), "Names should be equal");
        assertEquals(meetingAgenda1.getDescription(), listDto.get(0).getDescription(), "Description should be equal");
        assertEquals(meetingAgenda2.getName(), listDto.get(1).getName(), "Names should be equal");
        assertEquals(meetingAgenda2.getDescription(), listDto.get(1).getDescription(), "Description should be equal");
    }

    @Test
    public void listDtoFromListEntityNullParam() {
        List<MeetingAgendaDto> listDto = meetingAgendaConverter.listDtoFromListEntity(null);
        assertNull(listDto, "List dto should be null");
    }
}
