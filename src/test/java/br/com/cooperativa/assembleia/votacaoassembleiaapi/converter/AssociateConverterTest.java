package br.com.cooperativa.assembleia.votacaoassembleiaapi.converter;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.associate.AssociateForm;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.entity.Associate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssociateConverterTest {

    private AssociateConverter associateConverter;

    @BeforeEach
    public void setup() {
        associateConverter = new AssociateConverter();
    }

    @Test
    public void entityFromFormSuccess() {
        AssociateForm associateForm = new AssociateForm("Joao Carlos", "611.402.760-42");
        Associate associate = associateConverter.entityFromForm(associateForm);
        assertNotNull(associate, "Associate must be not null");
        assertEquals(associateForm.getName(), associate.getName(), "Associate names should be equal");
        assertEquals(associateForm.getCpf(), associate.getCpf(), "CPF should be equal");
    }

    @Test
    public void entityFromFormNullParam() {
        Associate associate = associateConverter.entityFromForm(null);
        assertNull(associate, "Associate must be null");
    }

    @Test
    public void dtoFromEntitySuccess() {
        Associate associate = new Associate("Entity from DB", "641.990.630-05");
        AssociateDto associateDto = associateConverter.dtoFromEntity(associate);
        assertNotNull(associateDto, "AssociateDto must be not null");
        assertEquals(associate.getName(), associateDto.getName(), "Associate names should be equal");
        assertEquals(associate.getCpf(), associateDto.getCpf(), "CPF should be equal");
        assertNull(associateDto.getId(), "Associate ID should be null");
    }

    @Test
    public void dtoFromEntityNullParam() {
        AssociateDto associateDto = associateConverter.dtoFromEntity(null);
        assertNull(associateDto, "AssociateDto should be null");
    }

    @Test
    public void listDtoFromListEntitySuccess() {
        Associate associate1 = new Associate("Associate 1", "641.990.630-05");
        Associate associate2 = new Associate("Associate 2", "433.766.100-02");
        List<Associate> listEntity = List.of(associate1, associate2);
        List<AssociateDto> listDto = associateConverter.listDtoFromListEntity(listEntity);

        assertNotNull(listDto, "List Dto should be not null");
        assertEquals(2, listDto.size(), "List Dto size should be 2");
        assertEquals(associate1.getName(), listDto.get(0).getName(), "Associate names should be equal");
        assertEquals(associate1.getCpf(), listDto.get(0).getCpf(), "CPF should be equal");
        assertEquals(associate2.getName(), listDto.get(1).getName(), "Associate names should be equal");
        assertEquals(associate2.getCpf(), listDto.get(1).getCpf(), "CPF should be equal");
    }

    @Test
    public void listDtoFromListEntityNullParam() {
        List<AssociateDto> listDto = associateConverter.listDtoFromListEntity(null);
        assertNull(listDto, "List dto should be null");
    }
}
