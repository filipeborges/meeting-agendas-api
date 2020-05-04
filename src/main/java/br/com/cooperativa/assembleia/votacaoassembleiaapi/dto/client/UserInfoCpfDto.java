package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.client;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.enums.client.CpfStatusEnum;

public class UserInfoCpfDto {
    private CpfStatusEnum status;

    public UserInfoCpfDto() {
    }

    public CpfStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CpfStatusEnum status) {
        this.status = status;
    }
}
