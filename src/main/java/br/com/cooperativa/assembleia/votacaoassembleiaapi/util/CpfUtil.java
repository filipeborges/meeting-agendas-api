package br.com.cooperativa.assembleia.votacaoassembleiaapi.util;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CpfUtil {
    public String normalizeCpf(String cpf) {
        return StringUtils.isEmpty(cpf)
                ? null : cpf.replace(".", "").replace("-", "");
    }
}
