package com.poly.ps08445.controller.api;

import com.poly.ps08445.JsonResponse.StaffDTOJsonResponse;
import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.services.DepartService;
import com.poly.ps08445.services.StaffService;
import com.poly.ps08445.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping(value = "/validate", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public StaffDTOJsonResponse validate(@RequestBody @Validated StaffDTO staffDTO, BindingResult result) {
        StaffDTOJsonResponse response = new StaffDTOJsonResponse();
        Map<String, String> errors = new HashMap<>();
        if (result.hasErrors()){
            Map<Integer, String> fieldErrorsOrder = new HashMap<>();
            Set<String> fieldErrors = new HashSet<>();
            fieldErrorsOrder.put(1, "fullName");
            fieldErrorsOrder.put(2, "gender");
            fieldErrorsOrder.put(3, "birthday");
            fieldErrorsOrder.put(4, "phone");
            fieldErrorsOrder.put(5, "email");
            fieldErrorsOrder.put(6, "salary");
            fieldErrorsOrder.put(7, "departId");
            fieldErrorsOrder.put(8, "photo");
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
            errors.put(firstFieldError, result.getFieldError(firstFieldError).getDefaultMessage());
            response.setErrorMessages(errors);
            response.setValidated(false);
        } else if (!staffDTO.getFullName().matches("[\\p{L}\\p{M}0-9 ]+")) {
            errors.put("name", "Họ tên không được chứa các ký tự đặc biệt");
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else if (!staffDTO.getPhone().matches("0[1-9][0-9]{8,9}")) {
            errors.put("phone", "Số điện thoại không đúng định dạng");
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else if (!staffDTO.getEmail().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
            errors.put("email", "Email không đúng định dạng");
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
        }
        return response;
    }

    @PostMapping(value = "/insert", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String insert(@RequestBody StaffDTO staffDTO){
        return staffService.insertStaff(staffDTO) + "";
    }

    @PostMapping(value = "/update", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public boolean update(@RequestBody StaffDTO staffDTO){
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
        System.out.println(list.size() + ".");
        for (StaffDTO staffDTO:list){
            System.out.println("zo");
            html.append("<tr>");
            html.append("<td><input type=\"checkbox\" value=\"").append(staffDTO.getId()).append("\"></td>");
            html.append("<td>").append(staffDTO.getFullName()).append("</td>");
            if (staffDTO.getGender() != null && staffDTO.getGender()){
                html.append("<td>Nam</td>");
            } else {
                html.append("<td>Nữ</td>");
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
