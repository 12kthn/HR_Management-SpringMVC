package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.services.DepartService;
import com.poly.ps08445.services.StaffService;
import com.poly.ps08445.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api/staff")
public class StaffApi {

    @Autowired
    StaffService staffService;

    @Autowired
    DepartService departService;

    @Autowired
    ServletContext context;

    @PostMapping(value = "/uploadImage")
    public boolean uploadImage(@RequestParam("image") MultipartFile image){
        if (image.isEmpty()){
            return false;
        }
        try {
            String path = context.getRealPath("/resources/admin/img/" + image.getOriginalFilename());
            image.transferTo(new File(path));
            System.out.println("uploaded to " + path);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping(value = "/count", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public int count(@RequestBody StaffDTO staffDTO){
        return staffService.getMaxPage(10, staffDTO);
    }

    @PostMapping(value = "/select", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String select(@RequestBody StaffDTO staffDTO){
        return createHtmlTableContent(staffDTO);
    }

    @PostMapping(value = "/search", produces = "text/plain; charset=UTF-8")
    public String search(@RequestParam("departId") Integer departId, @RequestParam("fullName") String fullName){
        List<StaffDTO> list = staffService.findByFullNameAndDepart(departId, fullName);
        StringBuilder html = new StringBuilder();
        html.append("<option value=\"-1\"></option>");
        for (StaffDTO staffDTO:list){
            html.append("<option value=\"").append(staffDTO.getId()).append("\">").append(staffDTO.getFullName()).append("</option>");
        }
        return html.toString();
    }

    @PostMapping(value = "/insert", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String insert(@RequestBody StaffDTO staffDTO){
        return staffService.insertStaff(staffDTO) + "";
    }

    @PostMapping(value = "/update", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public boolean update(@RequestBody StaffDTO staffDTO){
        System.out.println(staffDTO.toString());
        return staffService.updateStaff(staffDTO);
    }

    @PostMapping(value = "/delete", produces = "application/json")
    public boolean delete(@RequestBody StaffDTO staffDTO){
        String path = context.getRealPath("/resources/admin/img/" + staffService.findOneById(staffDTO.getId()).getPhoto());
        boolean result = staffService.deleteStaff(staffDTO);
        if (result){
            try {
                new File(path).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    private String createHtmlTableContent(StaffDTO dto){
        List<StaffDTO> list = staffService.findByFullNameAndDepart(dto);
        StringBuilder html = new StringBuilder();
        for (StaffDTO staffDTO:list){
            html.append("<tr>");
            html.append("<td><input type=\"checkbox\" value=\"").append(staffDTO.getId()).append("\"></td>");
            html.append("<td>").append(staffDTO.getFullName()).append("</td>");
            if (staffDTO.getGender() != null && staffDTO.getGender()){
                html.append("<td class=\"text-center\">Nam</td>");
            } else {
                html.append("<td class=\"text-center\">Nữ</td>");
            }
            html.append("<td>").append(staffDTO.getBirthday()).append("</td>");
            html.append("<td>").append(staffDTO.getPhone()).append("</td>");
            html.append("<td>").append(staffDTO.getEmail()).append("</td>");
            html.append("<td>").append(NumberUtil.formatDouble(staffDTO.getSalary())).append(" VNĐ").append("</td>");
            html.append("<td>").append(staffDTO.getDepartName()).append("</td>");
            html.append("<td class=\"hidden\">").append(staffDTO.getNotes()).append("</td>");
            html.append("<td class=\"hidden\">").append(staffDTO.getPhoto()).append("</td>");
            html.append("<td style=\"padding-top: 4px; padding-bottom: 4px;\"><button type=\"button\" class=\"btn btn-info btn-xs\" data-toggle=\"modal\" data-target=\"#myModal\" style=\"margin-left: 42%;\"><i class=\"fa fa-star-half-full\"></i></button></td>");
            html.append("</tr>");
        }
        return html.toString();
    }
}
