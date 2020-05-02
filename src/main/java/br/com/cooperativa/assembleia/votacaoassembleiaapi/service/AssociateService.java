package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface AssociateService {

    Associate create(@Valid AssociateDto associateDto);

    List<Associate> getAll();

    Associate findOne(@NotBlank String id);

    Associate update(@Valid AssociateDto associateDto, @NotBlank String id);

}
