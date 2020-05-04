package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda.*;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.MeetingAgendaService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.VoteService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/meeting-agendas")
@Validated
public class MeetingAgendaController extends AbstractController {

    @Autowired
    private MeetingAgendaService meetingAgendaService;

    @Autowired
    private VoteService voteService;

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

    // ============================= VOTES SUB RESOURCE =========================================== //

    @GetMapping("/{id}/votes/")
    public ResponseEntity<List<VoteDto>> retrieveAllVotesFromMeetingAgenda(
            @PathVariable("id") @NotBlank String meetingAgendaId
    ) {
        return ResponseEntity.ok(voteService.getAll(meetingAgendaId));
    }

    @PutMapping("/{id}/votes/{id-associate}")
    public ResponseEntity<VoteDto> replaceVoteFromMeetingAgenda(
            @RequestBody @NotNull @Valid VoteForm voteForm,
            @PathVariable("id") @NotBlank String meetingAgendaId,
            @PathVariable("id-associate") @NotBlank String associateId
    ) {
        return ResponseEntity.ok(voteService.update(voteForm, meetingAgendaId, associateId));
    }

    @Override
    public String getResourceBaseUri() {
        return "/meeting-agendas";
    }
}
