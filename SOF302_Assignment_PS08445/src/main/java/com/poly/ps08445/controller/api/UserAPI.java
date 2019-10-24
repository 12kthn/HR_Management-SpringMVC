package com.poly.ps08445.controller.api;

import com.poly.ps08445.JsonResponse.UserDTOJsonResponse;
import com.poly.ps08445.dto.UserDTO;
import com.poly.ps08445.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    UserService userService;

    @PostMapping(value = "/validateChangePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTOJsonResponse validateChangePassword(@RequestBody @Validated UserDTO userDTO,
                                                      BindingResult result, @SessionAttribute("USER") UserDTO userSession) {
        UserDTOJsonResponse response = new UserDTOJsonResponse();
        Map<String, String> error = new HashMap<>();
        if (result.hasErrors()){
            Map<Integer, String> fieldErrorsOrder = new HashMap<>();
            Set<String> fieldErrors = new HashSet<>();
            fieldErrorsOrder.put(1, "oldPassword");
            fieldErrorsOrder.put(2, "newPassword");
            fieldErrorsOrder.put(3, "confirmPassword");
            for (FieldError fieldError: result.getFieldErrors()){
                fieldErrors.add(fieldError.getField());
            }
            String firstFieldError = "";
            for (int i = 1; i <= fieldErrorsOrder.size(); i++){
                if (fieldErrors.contains(fieldErrorsOrder.get(i))){
                    firstFieldError = fieldErrorsOrder.get(i);
                    break;
                }
            }
            if (firstFieldError.equals("")){
                response.setValidated(true);
            } else {
                error.put(firstFieldError, result.getFieldError(firstFieldError).getDefaultMessage());
                response.setErrorMessages(error);
                response.setValidated(false);
            }
        } else if (!userDTO.getNewPassword().equals(userDTO.getConfirmPassword())){
            error.put("confirmPassword", "Xác nhận mật khẩu không chính xác");
            response.setValidated(false);
            response.setErrorMessages(error);
        } else if (!userSession.getPassword().equals(userDTO.getOldPassword())){
            error.put("oldPassword", "Mật khẩu cũ không chính xác");
            response.setValidated(false);
            response.setErrorMessages(error);
        } else {
            response.setValidated(true);
        }
        return response;
    }

    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean changePassword(HttpSession session, @SessionAttribute("USER") UserDTO userSession, @RequestBody UserDTO userDTO){
        userSession.setPassword(userDTO.getNewPassword());
        if (userService.updateUser(userSession)){
            session.setAttribute("USER", userSession);
            return true;
        }
        return false;
    }

}
