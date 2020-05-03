package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

public class Associate extends AbstractEntity {
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
