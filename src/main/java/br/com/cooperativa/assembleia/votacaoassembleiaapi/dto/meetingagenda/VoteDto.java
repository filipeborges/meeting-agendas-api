package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda;

public class VoteDto {
    private String associateId;
    private String vote;

    public VoteDto(String associateId, String vote) {
        this.associateId = associateId;
        this.vote = vote;
    }

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "associateId='" + associateId + '\'' +
                ", vote='" + vote + '\'' +
                '}';
    }
}
