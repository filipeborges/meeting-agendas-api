package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.Id;

public class Associate {
    @Id
    private String id;
    private String name;

    public Associate() {
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Associado{" +
                "id='" + id + '\'' +
                ", nome='" + name + '\'' +
                '}';
    }

    // TODO: Equals and HashCode
}
