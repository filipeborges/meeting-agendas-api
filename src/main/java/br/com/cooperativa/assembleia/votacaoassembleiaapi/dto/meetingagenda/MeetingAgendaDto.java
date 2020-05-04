package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda;

import java.util.List;

public class MeetingAgendaDto {
    private String id;
    private String name;
    private String description;
    private Long sessionExpireIn;
    private Long acceptedVotes;
    private Long rejectedVotes;
    private String result;
    private List<VoteDto> votes;

    public MeetingAgendaDto(String id, String name, String description, Long sessionExpireIn,
                            Long acceptedVotes, Long rejectedVotes, String result, List<VoteDto> votes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sessionExpireIn = sessionExpireIn;
        this.acceptedVotes = acceptedVotes;
        this.rejectedVotes = rejectedVotes;
        this.result = result;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSessionExpireIn() {
        return sessionExpireIn;
    }

    public void setSessionExpireIn(Long sessionExpireIn) {
        this.sessionExpireIn = sessionExpireIn;
    }

    public Long getAcceptedVotes() {
        return acceptedVotes;
    }

    public void setAcceptedVotes(Long acceptedVotes) {
        this.acceptedVotes = acceptedVotes;
    }

    public Long getRejectedVotes() {
        return rejectedVotes;
    }

    public void setRejectedVotes(Long rejectedVotes) {
        this.rejectedVotes = rejectedVotes;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<VoteDto> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteDto> votes) {
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MeetingAgendaDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sessionExpireIn=" + sessionExpireIn +
                ", acceptedVotes=" + acceptedVotes +
                ", rejectedVotes=" + rejectedVotes +
                ", result='" + result + '\'' +
                ", votes=" + votes +
                '}';
    }
}
