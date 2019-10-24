package com.poly.ps08445.dto;

import com.poly.ps08445.entities.Record;
import com.poly.ps08445.utils.TimeUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.List;

public class RecordDTO {
    private Integer id;
    @NotNull(message = "Vui lòng chọn nhân viên")
    private Integer staffId;
    private String staffFullName;
    private String staffEmail;
    @NotNull(message = "Vui lòng chọn loại đánh giá")
    private Boolean type;
    @NotEmpty(message = "Lý do không được để trống")
    private String reason;
    private String date;

    private Integer departId;
    private String departName;
    private Integer page;
    private Integer maxResults;
    private String totalPages;

    public RecordDTO() {
    }

    public RecordDTO(Record record) {
        this.id = record.getId();
        this.staffId = record.getStaff().getId();
        this.staffFullName = record.getStaff().getFullName();
        this.staffEmail = record.getStaff().getEmail();
        this.type = record.getType();
        this.reason = record.getReason();
        this.date = TimeUtil.toString(record.getDate());
        this.departName = record.getStaff().getDepart().getName();
    }

    public RecordDTO(Integer departId, String staffFullName, Integer page, Integer maxResults) {
        this.staffFullName = staffFullName;
        this.departId = departId;
        this.page = page;
        this.maxResults = maxResults;
    }

    public List<RecordDTO> getList(List<Record> listRecord){
        List<RecordDTO> list = new ArrayList<>();
        for (Record record: listRecord) {
            list.add(new RecordDTO(record));
        };
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public void setStaffFullName(String staffFullName) {
        this.staffFullName = staffFullName;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", staffFullName='" + staffFullName + '\'' +
                ", staffEmail='" + staffEmail + '\'' +
                ", type=" + type +
                ", reason='" + reason + '\'' +
                ", date='" + date + '\'' +
                ", departId=" + departId +
                ", departName='" + departName + '\'' +
                ", page=" + page +
                ", maxResults=" + maxResults +
                ", totalPages='" + totalPages + '\'' +
                '}';
    }
}
