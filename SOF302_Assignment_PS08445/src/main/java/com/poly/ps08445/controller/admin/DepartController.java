package com.poly.ps08445.controller.admin;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.services.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "admin/depart")
public class DepartController {

    @Autowired
    DepartService departService;

    @GetMapping
    public String defaultPage(){
        return "admin/depart";
    }

    @ModelAttribute("departs")
    public List<DepartDTO> getList(){
        return departService.findAll();
    }
}
