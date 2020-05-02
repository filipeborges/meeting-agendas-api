package br.com.cooperativa.assembleia.votacaoassembleiaapi.repository;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssociateRepository extends MongoRepository<Associate, String> {
}
