package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.util.JsonTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociateController.class)
@ActiveProfiles("test")
public class AssociateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateService associateService;

    private AssociateDto associateDto;
    private AssociateForm associateForm;

    @BeforeEach
    public void setup() {
        associateForm = new AssociateForm("Associate Name", "611.402.760-42");
        associateDto = new AssociateDto("123456", associateForm.getName(), associateForm.getCpf());
    }

    @Test
    public void retrieveAll200() throws Exception {
        List<AssociateDto> listResult = List.of(associateDto);
        when(associateService.getAll()).thenReturn(listResult);
        mockMvc.perform(get("/associates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveAll500() throws Exception {
        when(associateService.getAll()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/associates"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void newAssociate200() throws Exception {
        when(associateService.create(any())).thenReturn(associateDto);
        mockMvc.perform(
                post("/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void newAssociate400() throws Exception {
        associateForm.setName("");
        mockMvc.perform(
                post("/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void newAssociate500() throws Exception {
        when(associateService.create(any())).thenThrow(new RuntimeException());
        mockMvc.perform(
                post("/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void oneAssociate200() throws Exception {
        when(associateService.findOne(anyString())).thenReturn(associateDto);
        mockMvc.perform(get("/associates/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void oneAssociate500() throws Exception {
        when(associateService.findOne(anyString())).thenThrow(new RuntimeException());
        mockMvc.perform(get("/associates/123"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void replaceAssociate200() throws Exception {
        when(associateService.update(any(), anyString()))
                .thenReturn(associateDto);
        mockMvc.perform(
                put("/associates/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void replaceAssociate400() throws Exception {
        associateForm.setName("");
        mockMvc.perform(
                put("/associates/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void replaceAssociate404() throws Exception {
        when(associateService.update(any(), anyString()))
                .thenThrow(new ResourceNotFoundException("associates", "123"));
        mockMvc.perform(
                put("/associates/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    public void replaceAssociate500() throws Exception {
        when(associateService.update(any(), anyString()))
                .thenThrow(new RuntimeException());
        mockMvc.perform(
                put("/associates/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonTestUtil.jsonFromObject(associateForm))
        )
                .andExpect(status().isInternalServerError());
    }
}
