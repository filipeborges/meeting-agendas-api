package br.com.cooperativa.assembleia.votacaoassembleiaapi.repository;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.MeetingAgenda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MeetingAgendaRepository extends MongoRepository<MeetingAgenda, String> {
    @Query(value = "{ _id: ?0 }", fields = "{ votes: { $elemMatch: { associateId: ?1 } } }")
    Optional<MeetingAgenda> findVoteByMeetingAgendaIdAndAssociateId(String id, String associateId);
}
