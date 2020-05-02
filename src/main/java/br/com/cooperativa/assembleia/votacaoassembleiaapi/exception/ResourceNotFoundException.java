package br.com.cooperativa.assembleia.votacaoassembleiaapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String id) {
        super(String.format("Could not found %s - %s", resourceName, id));
    }
}
