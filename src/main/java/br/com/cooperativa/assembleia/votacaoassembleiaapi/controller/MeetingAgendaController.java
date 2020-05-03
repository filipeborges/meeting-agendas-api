package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.MeetingAgendaStartSessionForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/meeting-agendas")
@Validated
public class MeetingAgendaController extends AbstractController {

    @Autowired
    private MeetingAgendaService meetingAgendaService;

    @GetMapping
    @ApiOperation(
            value = "View a list of available meeting agendas",response = MeetingAgendaDto.class, responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<MeetingAgendaDto>> retrieveAll() {
        return ResponseEntity.ok(meetingAgendaService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new meeting agenda", response = MeetingAgendaDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Wrong payload data"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<MeetingAgendaDto> newMeetingAgenda(
            @ApiParam(value = "Meeting agenda basic information", required = true)
            @RequestBody @Valid MeetingAgendaForm meetingAgendaForm
    ) {
        MeetingAgendaDto meetingAgendaDto = meetingAgendaService.create(meetingAgendaForm);
        return ResponseEntity
                .created(buildNewResourceUri(meetingAgendaDto.getId()))
                .body(meetingAgendaDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View a specific meeting agenda", response = MeetingAgendaDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<MeetingAgendaDto> oneMeetingAgenda(
            @ApiParam(value = "Meeting agenda id", required = true)
            @PathVariable @NotBlank String id
    ) {
        return ResponseEntity.ok(meetingAgendaService.findOne(id));
    }

    @PatchMapping("/{id}/start-session")
    @ApiOperation(value = "Start meeting agenda voting session", response = MeetingAgendaDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully started voting session"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<MeetingAgendaDto> startMeetingAgendaSession(
            @ApiParam(value = "Meeting agenda start voting session information", required = false)
            @RequestBody(required = false) @Valid MeetingAgendaStartSessionForm meetingAgendaStartSessionForm,
            @ApiParam(value = "Meeting agenda id", required = true)
            @PathVariable @NotBlank String id
            ) {
        return ResponseEntity.ok(meetingAgendaService.startSession(meetingAgendaStartSessionForm, id));
    }

    @Override
    public String getResourceBaseUri() {
        return "/meeting-agendas";
    }
}
