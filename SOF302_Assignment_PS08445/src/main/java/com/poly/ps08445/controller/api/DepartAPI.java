package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.services.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/depart")
public class DepartAPI {

    @Autowired
    DepartService departService;

    @PostMapping(value = "/duplicateCode", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public boolean duplicateCode(@RequestBody DepartDTO departDTO) {
        return departService.duplicateCode(departDTO.getCode());
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
