package com.poly.ps08445.controller.admin;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.services.DepartService;
import com.poly.ps08445.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @Autowired
    DepartService departService;

    private int maxResults = 10;

    @GetMapping
    public String defaultPage(ModelMap model){
        StaffDTO staffDTO = new StaffDTO(-1, "", 1, maxResults);
        model.addAttribute("staffs", staffService.findByFullNameAndDepart(staffDTO));
        model.addAttribute("totalPages", staffService.getMaxPage(maxResults, staffDTO));
        return "/admin/staff";
    }

    @ModelAttribute("departs")
    public List<DepartDTO> getDeparts(){
        return departService.findAll();
    }

}
