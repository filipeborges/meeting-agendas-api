package br.com.cooperativa.assembleia.votacaoassembleiaapi.repository;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeetingAgendaRepository extends MongoRepository<MeetingAgenda, String> {
}
