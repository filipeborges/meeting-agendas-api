package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaStartSessionForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.VoteDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.VoteService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.util.JsonTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeetingAgendaController.class)
@ActiveProfiles("test")
public class MeetingAgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetingAgendaService meetingAgendaServiceMock;

    @MockBean
    private VoteService voteServiceMock;

    private MeetingAgendaDto meetingAgendaDto;
    private MeetingAgendaForm meetingAgendaForm;
    private MeetingAgendaStartSessionForm meetingAgendaStartSessionForm;
    private VoteDto voteDto;


    @BeforeEach
    public void setup() {
        meetingAgendaDto = new MeetingAgendaDto(
                "1234",
                "name",
                "description",
                System.currentTimeMillis() + 300000L,
                0L,
                0L,
                "tied",
                Collections.emptyList()
        );

        meetingAgendaForm = new MeetingAgendaForm("name", "description");
        voteDto = new VoteDto("1234", "accept");
        meetingAgendaStartSessionForm = new MeetingAgendaStartSessionForm();
    }

    @Test
    public void retrieveAll200() throws Exception {
        List<MeetingAgendaDto> listResult = List.of(meetingAgendaDto);
        when(meetingAgendaServiceMock.getAll()).thenReturn(listResult);
        mockMvc.perform(get("/meeting-agendas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveAll500() throws Exception {
        when(meetingAgendaServiceMock.getAll()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/meeting-agendas"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void newMeetingAgenda200() throws Exception {
        when(meetingAgendaServiceMock.create(any())).thenReturn(meetingAgendaDto);
        mockMvc.perform(
                post("/meeting-agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(meetingAgendaForm))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void newMeetingAgenda400() throws Exception {
        meetingAgendaForm.setName("");
        mockMvc.perform(
                post("/meeting-agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(meetingAgendaForm))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void newMeetingAgenda500() throws Exception {
        when(meetingAgendaServiceMock.create(any())).thenThrow(new RuntimeException());
        mockMvc.perform(
                post("/meeting-agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(meetingAgendaForm))
        )
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void oneMeetingAgenda200() throws Exception {
        when(meetingAgendaServiceMock.findOne(anyString())).thenReturn(meetingAgendaDto);
        mockMvc.perform(get("/meeting-agendas/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void oneMeetingAgenda404() throws Exception {
        when(meetingAgendaServiceMock.findOne(anyString())).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(get("/meeting-agendas/123"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void oneMeetingAgenda500() throws Exception {
        when(meetingAgendaServiceMock.findOne(anyString())).thenThrow(new RuntimeException());
        mockMvc.perform(get("/meeting-agendas/123"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void startMeetingAgendaSession200() throws Exception {
        when(meetingAgendaServiceMock.startSession(any(), any())).thenReturn(meetingAgendaDto);
        mockMvc.perform(
                patch("/meeting-agendas/123/start-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(meetingAgendaStartSessionForm))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void startMeetingAgendaSession404() throws Exception {
        when(meetingAgendaServiceMock.startSession(any(), any())).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(
                patch("/meeting-agendas/123/start-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(meetingAgendaStartSessionForm))
        )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void startMeetingAgendaSession500() throws Exception {
        when(meetingAgendaServiceMock.startSession(any(), any())).thenThrow(new RuntimeException());
        mockMvc.perform(
                patch("/meeting-agendas/123/start-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(meetingAgendaStartSessionForm))
        )
                .andExpect(status().isInternalServerError());
    }

}
