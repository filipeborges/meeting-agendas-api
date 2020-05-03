package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Associate {
    @Id
    private String id;
    private String name;
    private String cpf;
    @Version
    private Long version;

    public Associate() {
    }

    public Associate(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Associate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", version=" + version +
                '}';
    }
}
