package br.com.cooperativa.assembleia.votacaoassembleiaapi.exception;

import java.util.Date;

public class VotingSessionExpiredException extends RuntimeException {
    public VotingSessionExpiredException(Long sessionExpiredIn) {
        super(
                String.format(
                        "Voting session expired in - %s", new Date(sessionExpiredIn)
                )
        );
    }

    public VotingSessionExpiredException(String message) {
        super(message);
    }
}
