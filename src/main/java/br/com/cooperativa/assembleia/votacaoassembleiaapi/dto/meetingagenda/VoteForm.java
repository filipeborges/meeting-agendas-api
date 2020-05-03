package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.enums.VoteResultEnum;

import javax.validation.constraints.NotBlank;

public class VoteForm {
    private VoteResultEnum vote;

    public VoteForm() {
    }

    public VoteForm(VoteResultEnum vote) {
        this.vote = vote;
    }

    public VoteResultEnum getVote() {
        return vote;
    }

    public void setVote(VoteResultEnum vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VoteForm{" +
                "vote=" + vote +
                '}';
    }
}
