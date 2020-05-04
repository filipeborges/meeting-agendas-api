package br.com.cooperativa.assembleia.votacaoassembleiaapi.exception;

import java.util.Date;

public class VotingSessionExpiredException extends RuntimeException {
    public VotingSessionExpiredException(Long sessionStartedIn, Long sessionIntervalDuration) {
        super(
                String.format(
                        "Voting session expired in - %s", new Date(sessionStartedIn + sessionIntervalDuration)
                )
        );
    }
}
