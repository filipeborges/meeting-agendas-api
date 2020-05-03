package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.meetingagenda;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MeetingAgendaForm {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 300)
    private String description;

    public MeetingAgendaForm(@NotBlank @Size(max = 100) String name, @NotBlank @Size(max = 300) String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MeetingAgendaForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
