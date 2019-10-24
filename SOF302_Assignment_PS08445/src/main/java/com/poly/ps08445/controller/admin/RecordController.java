package com.poly.ps08445.controller.admin;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.dto.RecordDTO;
import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.entities.Record;
import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.services.DepartService;
import com.poly.ps08445.services.RecordService;
import com.poly.ps08445.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    DepartService departService;

    @Autowired
    StaffService staffService;

    @GetMapping
    public String display(ModelMap model){
        RecordDTO recordDTO = new RecordDTO(-1, "", 1, 10);
        model.addAttribute("records", recordService.findByStaffFullNameAndDepart(recordDTO));
        model.addAttribute("totalPages", recordService.getMaxPage(recordDTO));
        model.addAttribute("staffs", staffService.findAll());
        model.addAttribute("departs", departService.findAll());
        return "admin/record";
    }

}
