package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AssociateDto {
    @NotBlank
    @Size(max = 200)
    private String name;

    public AssociateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AssociateDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
