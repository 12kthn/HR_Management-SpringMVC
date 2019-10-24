package com.poly.ps08445.JsonResponse;

import com.poly.ps08445.dto.RecordDTO;

import java.util.Map;

public class RecordDTOJsonResponse {
    private RecordDTO recordDTO;
    private boolean validated;
    private Map<String, String> errorMessages;

    public RecordDTO getRecordDTO() {
        return recordDTO;
    }

    public void setRecordDTO(RecordDTO recordDTO) {
        this.recordDTO = recordDTO;
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
