package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.client.UserInfoClient;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.AssociateConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.client.UserInfoCpfDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.enums.client.CpfStatusEnum;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.AssociateUnableToVoteException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.AssociateRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.util.CpfUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class AssociateServiceImpl implements AssociateService {

    private static final String RESOURCE_NAME = "associate";

    private final AssociateRepository associateRepository;
    private final AssociateConverter associateConverter;
    private final UserInfoClient userInfoClient;
    private final CpfUtil cpfUtil;

    public AssociateServiceImpl(
            AssociateRepository associateRepository,
            AssociateConverter associateConverter,
            UserInfoClient userInfoClient,
            CpfUtil cpfUtil
    ) {
        this.associateRepository = associateRepository;
        this.associateConverter = associateConverter;
        this.userInfoClient = userInfoClient;
        this.cpfUtil = cpfUtil;
    }

    @Override
    public AssociateDto create(@NotNull @Valid AssociateForm associateForm) {
        return associateConverter.dtoFromEntity(
                associateRepository.save(associateConverter.entityFromForm(associateForm))
        );
    }

    @Override
    public List<AssociateDto> getAll() {
        return associateConverter.listDtoFromListEntity(associateRepository.findAll());
    }

    @Override
    public AssociateDto findOne(@NotBlank String id) {
        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
        return associateConverter.dtoFromEntity(associate);
    }

    @Override
    public AssociateDto update(@NotNull @Valid AssociateForm associateForm, @NotBlank String id) {
        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));
        associate.setName(associateForm.getName());
        return associateConverter.dtoFromEntity(associateRepository.save(associate));
    }

    @Override
    public void verifyIfAssociateExists(@NotBlank String id) {
        if (!associateRepository.existsById(id)) throw new ResourceNotFoundException(RESOURCE_NAME, id);
    }

    @Override
    public void verifyIfAssociateIsAbleToVote(@NotBlank String id) {
        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, id));

        UserInfoCpfDto userInfoCpfDto = userInfoClient.verifyUserCpf(
                cpfUtil.normalizeCpf(associate.getCpf())
        );
        if (userInfoCpfDto.getStatus() != CpfStatusEnum.ABLE_TO_VOTE) throw new AssociateUnableToVoteException(id);
    }

}
