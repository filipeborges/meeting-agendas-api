package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Associate>> retrieveAll() {
        return ResponseEntity.ok(associateService.getAll());
    }

    @PostMapping
    public ResponseEntity<Associate> newAssociate(@RequestBody @Valid AssociateDto associateDto) {
        Associate newAssociate = associateService.create(associateDto);
        return ResponseEntity
                .created(buildNewResourceUri(newAssociate.getId()))
                .body(newAssociate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Associate> oneAssociate(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(associateService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Associate> replaceAssociate(
            @RequestBody @Valid AssociateDto associateDto,
            @PathVariable @NotBlank String id
    ) {
        return ResponseEntity.ok(associateService.update(associateDto, id));
    }

    @Override
    public String getResourceBaseUri() {
        return "/associates";
    }
}
