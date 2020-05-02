package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Associate {
    @Id
    private String id;
    private String name;
    @Version
    private Long version;

    public Associate() {
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
        return "Associate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }

}
