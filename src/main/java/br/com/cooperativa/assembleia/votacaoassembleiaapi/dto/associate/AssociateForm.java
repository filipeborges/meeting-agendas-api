package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AssociateForm {
    @NotBlank
    @Size(max = 200)
    private String name;

    public AssociateForm() {
    }

    public AssociateForm(@NotBlank @Size(max = 200) String name) {
        this.name = name;
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
