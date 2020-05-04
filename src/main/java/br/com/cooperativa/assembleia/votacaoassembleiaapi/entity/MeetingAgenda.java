package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;
import java.util.List;

public class MeetingAgenda extends AbstractEntity {
    private String name;
    private String description;
    private Long sessionStartedIn;
    private Long sessionIntervalDuration;
    private Long acceptedVotes;
    private Long rejectedVotes;
    private String result;
    private List<Vote> votes;

    @PersistenceConstructor
    public MeetingAgenda(String name, String description, Long sessionStartedIn, Long sessionIntervalDuration,
                         Long acceptedVotes, Long rejectedVotes, String result, List<Vote> votes,
                         String id, Long version) {
        this.name = name;
        this.description = description;
        this.sessionStartedIn = sessionStartedIn;
        this.sessionIntervalDuration = sessionIntervalDuration;
        this.acceptedVotes = acceptedVotes;
        this.rejectedVotes = rejectedVotes;
        this.result = result;
        this.id = id;
        this.version = version;
        this.votes = votes;
    }

    public MeetingAgenda(String name, String description) {
        this.name = name;
        this.description = description;
        this.votes = new ArrayList<>();
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

    public Long getSessionStartedIn() {
        return sessionStartedIn;
    }

    public void setSessionStartedIn(Long sessionStartedIn) {
        this.sessionStartedIn = sessionStartedIn;
    }

    public Long getSessionIntervalDuration() {
        return sessionIntervalDuration;
    }

    public void setSessionIntervalDuration(Long sessionIntervalDuration) {
        this.sessionIntervalDuration = sessionIntervalDuration;
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

    public List<Vote> getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "MeetingAgenda{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sessionStartedIn=" + sessionStartedIn +
                ", sessionIntervalDuration=" + sessionIntervalDuration +
                ", acceptedVotes=" + acceptedVotes +
                ", rejectedVotes=" + rejectedVotes +
                ", result='" + result + '\'' +
                ", votes=" + votes +
                ", id='" + id + '\'' +
                ", version=" + version +
                '}';
    }
}
