package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingAgenda extends AbstractEntity {
    private String name;
    private String description;
    private Long sessionExpireIn;
    private Long acceptedVotes;
    private Long rejectedVotes;
    private String result;
    private List<Vote> votes;

    @PersistenceConstructor
    public MeetingAgenda(String name, String description, Long sessionExpireIn, Long acceptedVotes,
                         Long rejectedVotes, String result, List<Vote> votes) {
        this.name = name;
        this.description = description;
        this.sessionExpireIn = sessionExpireIn;
        this.acceptedVotes = acceptedVotes;
        this.rejectedVotes = rejectedVotes;
        this.result = result;
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

    public List<Vote> getVotes() {
        return votes;
    }

    @Transient
    public boolean isSessionOpen() {
        long currentTime = new Date().getTime();
        return sessionExpireIn != null
                && ( currentTime <= sessionExpireIn );
    }

    @Override
    public String toString() {
        return "MeetingAgenda{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sessionExpireIn=" + sessionExpireIn +
                ", acceptedVotes=" + acceptedVotes +
                ", rejectedVotes=" + rejectedVotes +
                ", result='" + result + '\'' +
                ", votes=" + votes +
                '}';
    }
}
