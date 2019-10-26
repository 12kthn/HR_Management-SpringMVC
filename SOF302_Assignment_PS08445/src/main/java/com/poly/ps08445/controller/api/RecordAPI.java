package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.RecordDTO;
import com.poly.ps08445.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/record")
public class RecordAPI {

    @Autowired
    RecordService recordService;

    @PostMapping(value = "/select", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String select(@RequestBody RecordDTO recordDTO){
        return createHtmlTableContent(recordDTO);
    }

    @PostMapping(value = "/count", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public int count(@RequestBody RecordDTO recordDTO){
        return recordService.getMaxPage(recordDTO);
    }

    @PostMapping(value = "insert", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String insert(@RequestBody RecordDTO recordDTO){
        return recordService.insertRecord(recordDTO) + "";
    }

    @PostMapping(value = "/update", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public boolean update(@RequestBody RecordDTO recordDTO){
        return recordService.updateRecord(recordDTO);
    }

    @PostMapping(value = "/delete", produces = "application/json", consumes = "application/json; charset=UTF-8")
    public boolean delete(@RequestBody RecordDTO recordModel){
        return recordService.deleteRecord(recordModel);
    }

    private String createHtmlTableContent(RecordDTO dto){
        List<RecordDTO> list = recordService.findByStaffFullNameAndDepart(dto);
        StringBuilder html = new StringBuilder();
        for (RecordDTO recordDTO : list){
            html.append("<tr>");
            html.append("<td><input type=\"checkbox\" value=\"").append(recordDTO.getId()).append("\"></td>");
            html.append("<td style=\"display: none; max-width: 0; max-height: 0\">").append(recordDTO.getStaffId()).append("</td>");
            html.append("<td>").append(recordDTO.getStaffFullName()).append("</td>");
            html.append("<td>").append(recordDTO.getDepartName()).append("</td>");
            if (recordDTO.getType() != null && recordDTO.getType()){
                html.append("<td class=\"text-center\">Thành tích</td>");
            } else {
                html.append("<td class=\"text-center\">Kỷ luật</td>");
            }
            html.append("<td>").append(recordDTO.getReason()).append("</td>");
            html.append("<td class=\"text-center\">").append(recordDTO.getDate()).append("</td>");
            html.append("<td style=\"padding-top: 4px; padding-bottom: 4px;\"><button type=\"button\" class=\"btn btn-info btn-xs\" data-toggle=\"modal\" data-target=\"#myModal\" style=\"margin-left: 42%;\"><i class=\"fa fa-mail-forward\"></i></button></td>");
            html.append("<td class=\"hidden\">").append(recordDTO.getStaffEmail()).append("</td>");
            html.append("</tr>");
        }
        return html.toString();
    }

}
