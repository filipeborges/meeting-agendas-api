package br.com.cooperativa.assembleia.votacaoassembleiaapi.dto;

import java.util.Date;

public class ControllerExceptionAdviceDto {
    private Date timestamp;
    private String message;

    public ControllerExceptionAdviceDto(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ControllerExceptionAdviceDto{" +
                "timestamp='" + timestamp + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
