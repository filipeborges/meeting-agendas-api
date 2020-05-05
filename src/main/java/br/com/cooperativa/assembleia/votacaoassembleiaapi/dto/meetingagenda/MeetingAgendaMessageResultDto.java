package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda;

import java.io.Serializable;

public class MeetingAgendaMessageResultDto implements Serializable {

    private static final long serialVersionUID = 2512340556923697036L;

    private String name;
    private Long acceptedVotes;
    private Long rejectedVotes;
    private String result;

    public MeetingAgendaMessageResultDto(String name, Long acceptedVotes, Long rejectedVotes, String result) {
        this.name = name;
        this.acceptedVotes = acceptedVotes;
        this.rejectedVotes = rejectedVotes;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "MeetingAgendaMessageResultDto{" +
                "name='" + name + '\'' +
                ", acceptedVotes=" + acceptedVotes +
                ", rejectedVotes=" + rejectedVotes +
                ", result='" + result + '\'' +
                '}';
    }
}
