package com.poly.ps08445.JsonResponse;

import com.poly.ps08445.dto.EmailDTO;

import java.util.Map;

public class EmailDTOJsonResponse {
    private EmailDTO emailDTO;
    private boolean validated;
    private Map<String, String> errorMessages;

    public EmailDTO getEmailDTO() {
        return emailDTO;
    }

    public void setEmailDTO(EmailDTO emailDTO) {
        this.emailDTO = emailDTO;
    }

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
