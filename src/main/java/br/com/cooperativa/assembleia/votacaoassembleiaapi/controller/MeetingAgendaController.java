package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/meeting-agendas")
@Validated
public class MeetingAgendaController extends AbstractController {

    @Autowired
    private MeetingAgendaService meetingAgendaService;

    @GetMapping
    public ResponseEntity<List<MeetingAgendaDto>> retrieveAll() {
        return ResponseEntity.ok(meetingAgendaService.getAll());
    }

    @PostMapping
    public ResponseEntity<MeetingAgendaDto> newMeetingAgenda(
            @RequestBody @Valid MeetingAgendaForm meetingAgendaForm
    ) {
        MeetingAgendaDto meetingAgendaDto = meetingAgendaService.create(meetingAgendaForm);
        return ResponseEntity
                .created(buildNewResourceUri(meetingAgendaDto.getId()))
                .body(meetingAgendaDto);
    }

    @Override
    public String getResourceBaseUri() {
        return "/meeting-agendas";
    }
}
