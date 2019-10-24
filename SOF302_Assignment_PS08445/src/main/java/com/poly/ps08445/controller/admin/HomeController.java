package com.poly.ps08445.controller.admin;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.dto.DepartScoreDTO;
import com.poly.ps08445.dto.StaffScoreDTO;
import com.poly.ps08445.services.DepartService;
import com.poly.ps08445.services.StoredProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller(value = "admin")
@RequestMapping(value = "/admin/home")
public class HomeController {

    @Autowired
    StoredProcedureService storedService;

    @Autowired
    DepartService departService;

    @GetMapping
    public String homePage() {
        return "admin/home";
    }

    @ModelAttribute("departTotalScore")
    public List<DepartScoreDTO> getListDepart() {
        return storedService.getDepartTotalScore();
    }

    @ModelAttribute("staffTotalScore")
    public List<StaffScoreDTO> getListStaff() {
        return storedService.getStaffTotalScore(new StaffScoreDTO(-1, "", 1, 10));
    }

    @ModelAttribute("totalPages")
    public int getTotalPages() {
        return (int) Math.ceil(storedService.getStaffTotalScore(new StaffScoreDTO(-1, "", 1, 10000)).size() / 10.0);
    }

    @ModelAttribute("departs")
    public List<DepartDTO> getDeparts(){
        return departService.findAll();
    }

}
