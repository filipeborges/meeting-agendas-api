package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import javax.validation.constraints.NotBlank;
import java.net.URI;

public abstract class AbstractController {

    public abstract String getResourceBaseUri();

    public URI buildNewResourceUri(@NotBlank String id) {
        return URI.create(String.format("%s/%s", getResourceBaseUri(), id));
    }
}
