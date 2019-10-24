package com.poly.ps08445.JsonResponse;

import com.poly.ps08445.entities.User;

import java.util.Map;

public class UserDTOJsonResponse {
    private User user;
    private boolean validated;
    private Map<String, String> errorMessages;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
