package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AssociateForm {
    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    public AssociateForm() {
    }

    public AssociateForm(@NotBlank @Size(max = 200) String name, @NotBlank @CPF String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "AssociateForm{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
