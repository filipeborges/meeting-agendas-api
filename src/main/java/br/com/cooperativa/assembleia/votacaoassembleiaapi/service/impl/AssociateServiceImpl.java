package br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.AssociateRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateServiceImpl implements AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Override
    public Associate create(String associateName) {
        Associate newAssociate = new Associate(); // TODO: AssociateBuilder
        newAssociate.setName(associateName);
        return associateRepository.save(newAssociate);
    }
}
