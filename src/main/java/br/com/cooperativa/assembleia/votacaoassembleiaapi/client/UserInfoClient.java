package br.com.cooperativa.assembleia.votacaoassembleiaapi.client;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.client.UserInfoCpfDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-info", url = "${app.rest-client.user-info.host}")
public interface UserInfoClient {
    @GetMapping("/users/{cpf}")
    UserInfoCpfDto verifyUserCpf(@PathVariable("cpf") String userCpf);
}
