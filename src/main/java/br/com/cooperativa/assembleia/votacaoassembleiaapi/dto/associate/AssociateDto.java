package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate;

public class AssociateDto {
    String id;
    String name;
    String cpf;

    public AssociateDto(String id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
