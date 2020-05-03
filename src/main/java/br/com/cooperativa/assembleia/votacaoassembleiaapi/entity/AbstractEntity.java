package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public abstract class AbstractEntity {
    @Id
    protected String id;
    @Version
    protected Long version;

    public String getId() {
        return id;
    }
}
