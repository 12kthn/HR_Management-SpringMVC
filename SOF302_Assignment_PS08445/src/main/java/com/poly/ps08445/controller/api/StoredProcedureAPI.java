package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.StaffScoreDTO;
import com.poly.ps08445.services.StoredProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storedProcedure")
public class StoredProcedureAPI {

    @Autowired
    StoredProcedureService stored;

    private int startRank = 0;

    @PostMapping(value = "/select", produces = "text/plain; charset=UTF-8", consumes = "application/json; charset=UTF-8")
    public String select(@RequestBody StaffScoreDTO staffScoreDTO){
        List<StaffScoreDTO> list = stored.getStaffTotalScore(staffScoreDTO);
        startRank = (staffScoreDTO.getPage() - 1)*staffScoreDTO.getMaxResults();
        return createHtmlTableContent(list);
    }

    @PostMapping(value = "/getTotalPages", produces = "application/json; charset=UTF-8")
    public int getTotalPages(@RequestBody StaffScoreDTO dto){
        List<StaffScoreDTO> list = stored.getStaffTotalScore(new StaffScoreDTO(dto.getDepartId(), dto.getStaffFullName(), 1, 1000000));
        return (int) Math.ceil(list.size()/(double)dto.getMaxResults());
    }

    private String createHtmlTableContent(List<StaffScoreDTO> list){
        if (list.size() == 0){
            return "";
        }
        StringBuilder html = new StringBuilder();
        for (StaffScoreDTO dto : list){
            startRank++;
            html.append("<tr>");
            html.append("<td class=\"text-center\">").append(startRank).append("</td>");
            html.append("<td>").append(dto.getStaffFullName()).append("</td>");
            html.append("<td>").append(dto.getDepartName()).append("</td>");
            html.append("<td class=\"text-center\">").append(dto.getTongThanhTich()).append("</td>");
            html.append("<td class=\"text-center\">").append(dto.getTongKyLuat()).append("</td>");
            html.append("<td class=\"text-center\">").append(dto.getTongDiem()).append("</td>");
            html.append("</tr>");
        }
        return html.toString();
    }

}
