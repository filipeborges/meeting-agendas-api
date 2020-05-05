package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Associate {
    @Id
    protected String id;
    @Version
    protected Long version;
    private String name;
    private String cpf;

    public Associate() {
    }

    public Associate(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Associate{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", id='" + id + '\'' +
                ", version=" + version +
                '}';
    }
}
