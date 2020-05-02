package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import org.springframework.stereotype.Service;

@Service
public class AssociateConverter {
    public Associate entityFromDto(AssociateDto associateDto) {
        Associate associate = new Associate();
        associate.setName(associateDto.getName());
        return associate;
    }

}
