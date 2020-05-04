package br.com.cooperativa.assembleia.votacaoassembleiaapi.service;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.client.UserInfoClient;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.converter.AssociateConverter;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.client.UserInfoCpfDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.enums.client.CpfStatusEnum;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.AssociateUnableToVoteException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.repository.AssociateRepository;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.service.impl.AssociateServiceImpl;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.util.CpfUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AssociateServiceTest {
    private AssociateRepository associateRepositoryMock;
    private AssociateConverter associateConverterMock;
    private UserInfoClient userInfoClientMock;
    private CpfUtil cpfUtilMock;

    private AssociateService associateService;

    private Associate associate;
    private UserInfoCpfDto userInfoCpfDto;

    @BeforeEach
    public void setup() {
        associateRepositoryMock = Mockito.mock(AssociateRepository.class);
        associateConverterMock = Mockito.mock(AssociateConverter.class);
        userInfoClientMock = Mockito.mock(UserInfoClient.class);
        cpfUtilMock = Mockito.mock(CpfUtil.class);
        associateService = new AssociateServiceImpl(
                associateRepositoryMock,
                associateConverterMock,
                userInfoClientMock,
                cpfUtilMock
        );
        associate = buildAssociate();
        userInfoCpfDto = buildUserInfoCpfDto();
    }

    @Test
    public void verifyIfAssociateIsAbleToVoteSuccess() {
        userInfoCpfDto.setStatus(CpfStatusEnum.ABLE_TO_VOTE);
        when(associateRepositoryMock.findById(anyString()))
                .thenReturn(Optional.of(associate));
        when(userInfoClientMock.verifyUserCpf(any()))
                .thenReturn(userInfoCpfDto);

        assertDoesNotThrow(() -> associateService.verifyIfAssociateIsAbleToVote("1234"));
    }

    @Test
    public void verifyIfAssociateIsAbleToVoteUnableFail() {
        userInfoCpfDto.setStatus(CpfStatusEnum.UNABLE_TO_VOTE);
        when(associateRepositoryMock.findById(anyString()))
                .thenReturn(Optional.of(associate));
        when(userInfoClientMock.verifyUserCpf(any()))
                .thenReturn(userInfoCpfDto);

        assertThrows(
                AssociateUnableToVoteException.class,
                () -> associateService.verifyIfAssociateIsAbleToVote("1234")
        );
    }

    private Associate buildAssociate() {
        return new Associate("nome", "951.563.710-43");
    }

    private UserInfoCpfDto buildUserInfoCpfDto() {
        return new UserInfoCpfDto();
    }
}
