package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.AssociateConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.AssociateRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Validated
public class AssociateServiceImpl implements AssociateService {

    private static final String RESOURCE_NAME = "associate";

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private AssociateConverter associateConverter;

    @Override
    public Associate create(@Valid AssociateDto associateDto) {
        return associateRepository.save(associateConverter.entityFromDto(associateDto));
    }

    @Override
    public List<Associate> getAll() {
        return associateRepository.findAll();
    }

    @Override
    public Associate findOne(@NotBlank String id) {
        return associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
    }

    @Override
    public Associate update(@Valid AssociateDto associateDto, @NotBlank String id) {
        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
        associate.setName(associateDto.getName()); // TODO: Builder Method
        return associateRepository.save(associate);
    }
}
