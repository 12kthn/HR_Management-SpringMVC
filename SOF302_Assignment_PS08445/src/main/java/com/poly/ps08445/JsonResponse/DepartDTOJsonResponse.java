package com.poly.ps08445.JsonResponse;

import com.poly.ps08445.dto.DepartDTO;

import java.util.Map;

public class DepartDTOJsonResponse {

    private DepartDTO departDTO;
    private Boolean validated;
    private Map<String, String> errorMessages;

    public DepartDTO getDepartDTO() {
        return departDTO;
    }

    public void setDepartDTO(DepartDTO departDTO) {
        this.departDTO = departDTO;
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
