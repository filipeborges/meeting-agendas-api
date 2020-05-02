package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
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
@RequestMapping("/associates")
@Validated
public class AssociateController extends AbstractController {

    @Autowired
    private AssociateService associateService;

    @GetMapping
    @ApiOperation(value = "View a list of available associates", response = AssociateDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<AssociateDto>> retrieveAll() {
        return ResponseEntity.ok(associateService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new associate", response = AssociateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Wrong payload data"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<AssociateDto> newAssociate(
            @ApiParam(value = "Associate data", required = true)
            @RequestBody @Valid AssociateForm associateForm
    ) {
        AssociateDto newAssociate = associateService.create(associateForm);
        return ResponseEntity
                .created(buildNewResourceUri(newAssociate.getId()))
                .body(newAssociate);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View a specific associate", response = AssociateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<AssociateDto> oneAssociate(
            @ApiParam(value = "Associate Id", required = true)
            @PathVariable @NotBlank String id
    ) {
        return ResponseEntity.ok(associateService.findOne(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a specific associate", response = AssociateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Wrong payload data"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<AssociateDto> replaceAssociate(
            @ApiParam(value = "Associate data", required = true)
            @RequestBody @Valid AssociateForm associateForm,
            @ApiParam(value = "Associate Id", required = true)
            @PathVariable @NotBlank String id
    ) {
        return ResponseEntity.ok(associateService.update(associateForm, id));
    }

    @Override
    public String getResourceBaseUri() {
        return "/associates";
    }
}
