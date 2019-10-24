package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.JsonResponse.DepartDTOJsonResponse;
import com.poly.ps08445.services.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/depart")
public class DepartAPI {

    @Autowired
    DepartService departService;

    @PostMapping(value = "/duplicateCode", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public boolean duplicateCode(@RequestBody DepartDTO departDTO) {
        return departService.duplicateCode(departDTO.getCode());
    }

    @PostMapping(value = "/validate", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public DepartDTOJsonResponse validate(@RequestBody @Validated DepartDTO departDTO, BindingResult result) {
        DepartDTOJsonResponse response = new DepartDTOJsonResponse();
        Map<String, String> errors = new HashMap<>();
        if (result.hasErrors()) {
            Map<Integer, String> fieldErrorsOrder = new HashMap<>();
            Set<String> fieldErrors = new HashSet<>();
            fieldErrorsOrder.put(1, "code");
            fieldErrorsOrder.put(2, "name");
            for (FieldError fieldError : result.getFieldErrors()) {
                fieldErrors.add(fieldError.getField());
            }
            String firstFieldError = "";
            for (int i = 1; i <= fieldErrorsOrder.size(); i++) {
                if (fieldErrors.contains(fieldErrorsOrder.get(i))) {
                    firstFieldError = fieldErrorsOrder.get(i);
                    break;
                }
            }
            errors.put(firstFieldError, result.getFieldError(firstFieldError).getDefaultMessage());
            response.setErrorMessages(errors);
            response.setValidated(false);
        } else if (!departDTO.getCode().matches("[A-Z]{2}")) {
            errors.put("code", "Mã phòng ban chỉ chứa 2 ký tự in hoa");
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else if (!departDTO.getName().matches("[\\p{L}\\p{M}0-9 ]+")) {
            errors.put("name", "Tên phòng ban không được chứa các ký tự đặc biệt");
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
        }
        return response;
    }

    @PostMapping(value = "/selectAll", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String selectALL() {
        return getHtmlTableDepart();
    }

    @PostMapping(value = "/insert", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String insert(@RequestBody DepartDTO departDTO) {
        if (departService.insertDepart(departDTO)) {
            return getHtmlTableDepart();
        } else {
            return "";
        }
    }

    @PostMapping(value = "/update", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String update(@RequestBody DepartDTO departDTO) {
        if (departService.updateDepart(departDTO)) {
            System.out.println("update success");
            return getHtmlTableDepart();
        } else {
            return "";
        }
    }

    @PostMapping(value = "/delete", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String delete(@RequestBody DepartDTO departDTO) {
        if (departService.deleteDepart(departDTO)) {
            return getHtmlTableDepart();
        } else {
            return "";
        }
    }

    private String getHtmlTableDepart() {
        List<DepartDTO> list = departService.findAll();
        StringBuilder html = new StringBuilder();
        for (DepartDTO depart : list) {
            html.append("<tr>");
            html.append("<td><input type=\"checkbox\" value=\"").append(depart.getId()).append("\"></td>");
            html.append("<td>").append(depart.getCode()).append("</td>");
            html.append("<td>").append(depart.getName()).append("</td>");
            html.append("</tr>");
        }
        return html.toString();
    }

}
