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
        if (associateForm == null) return null;
        return new Associate(associateForm.getName(), associateForm.getCpf());
    }

    public AssociateDto dtoFromEntity(Associate associate) {
        if (associate == null) return null;
        return new AssociateDto(associate.getId(), associate.getName(), associate.getCpf());
    }

    public List<AssociateDto> listDtoFromListEntity(List<Associate> associates) {
        if (associates == null) return null;
        return associates
                .stream()
                .map(
                        associate -> new AssociateDto(associate.getId(), associate.getName(), associate.getCpf())
                )
                .collect(Collectors.toList());
    }

}
