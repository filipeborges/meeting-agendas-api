package br.com.cooperativa.assembleia.votacaoassembleiaapi.exception;

public class AssociateUnableToVoteException extends RuntimeException {
    public AssociateUnableToVoteException(String associateId) {
        super(String.format("Associate %s contains a CPF unable to vote", associateId));
    }
}
