package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateConverter {

    public Associate entityFromForm(AssociateForm associateForm) {
        Associate associate = new Associate();
        associate.setName(associateForm.getName());
        return associate;
    }

    public AssociateDto dtoFromEntity(Associate associate) {
        return new AssociateDto(associate.getId(), associate.getName());
    }

    public List<AssociateDto> listDtoFromListEntity(List<Associate> associates) {
        return associates
                .stream()
                .map(
                        associate -> new AssociateDto(associate.getId(), associate.getName())
                )
                .collect(Collectors.toList());
    }

}