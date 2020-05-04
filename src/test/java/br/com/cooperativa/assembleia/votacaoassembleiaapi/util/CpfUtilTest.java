package br.com.cooperativa.assembleia.votacaoassembleiaapi.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CpfUtilTest {
    private CpfUtil cpfUtil;

    @BeforeEach
    public void setup() {
        cpfUtil = new CpfUtil();
    }

    @Test
    public void CpfUtilSuccess() {
        final String cpf = "951.563.710-43";
        final String resultCpf = cpfUtil.normalizeCpf(cpf);
        assertNotNull(resultCpf, "Result cpf must not be null");
        assertEquals("95156371043", resultCpf , "Cpf must be equal");
    }

    @Test
    public void CpfUtilEmptyParam() {
        final String cpf = "";
        final String resultCpf = cpfUtil.normalizeCpf(cpf);
        assertNull(resultCpf, "Result cpf must be null");
    }

}
