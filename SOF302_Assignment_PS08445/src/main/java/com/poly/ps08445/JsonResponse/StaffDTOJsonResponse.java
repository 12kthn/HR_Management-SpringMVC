package com.poly.ps08445.JsonResponse;

import com.poly.ps08445.dto.StaffDTO;

import java.util.Map;

public class StaffDTOJsonResponse {
    private StaffDTO staffDTO;
    private boolean validated;
    private Map<String, String> errorMessages;

    public StaffDTO getStaffDTO() {
        return staffDTO;
    }

    public void setStaffDTO(StaffDTO staffDTO) {
        this.staffDTO = staffDTO;
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
