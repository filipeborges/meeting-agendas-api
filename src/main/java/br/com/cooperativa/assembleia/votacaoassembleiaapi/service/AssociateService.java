package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AssociateService {

    AssociateDto create(@NotNull @Valid AssociateForm associateForm);

    List<AssociateDto> getAll();

    AssociateDto findOne(@NotBlank String id);

    AssociateDto update(@NotNull @Valid AssociateForm associateForm, @NotBlank String id);

    void verifyIfAssociateExists(@NotBlank String id);
}
