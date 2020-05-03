package br.com.cooperativa.assembleia.votacaoassembleiaapi.entity;

public class Vote {
    private String associateId;
    private String value;

    public Vote(String associateId, String value) {
        this.associateId = associateId;
        this.value = value;
    }

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "associateId='" + associateId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
