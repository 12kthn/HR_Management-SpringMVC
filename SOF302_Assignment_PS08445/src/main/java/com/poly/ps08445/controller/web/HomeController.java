package com.poly.ps08445.controller.web;

import com.poly.ps08445.dto.StaffScoreDTO;
import com.poly.ps08445.services.StoredProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "web")
public class HomeController {

    @Autowired
    StoredProcedureService stored;

    @RequestMapping(value = "/home")
    public String homePage(ModelMap model){
        model.addAttribute("staffs", stored.getStaffTotalScore(new StaffScoreDTO(-1, "", 1, 10)));
        return "web/home";
    }

}
