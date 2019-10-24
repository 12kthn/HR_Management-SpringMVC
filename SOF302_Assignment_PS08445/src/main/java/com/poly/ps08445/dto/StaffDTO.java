package com.poly.ps08445.dto;

import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.utils.TimeUtil;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class StaffDTO {
    private Integer id;
    @NotEmpty(message = "Tên nhân viên không được để trống")
    private String fullName;
    @NotNull(message = "Vui lòng chọn giới tính")
    private Boolean gender;
    @NotEmpty(message = "Vui lòng chọn ngày sinh")
    private String birthday;
    @NotEmpty(message = "Vui lòng chọn ảnh")
    private String photo;
    @NotEmpty(message = "Email không được để trống")
    private String email;
    @NotEmpty(message = "Số điện thoại không được để trống")
    private String phone;
    @Min(value = 1000000, message = "Lương phải là số nguyên lớn hơn 1 triệu")
    private double salary;
    private String notes;
    @NotNull(message = "Vui lòng chọn phòng ban")
    private Integer departId;
    private String departName;
    private Integer page;
    private Integer maxResults;
    private String totalPages;

    public StaffDTO() {
    }

    public StaffDTO(Integer id) {
        this.id = id;
    }

    public StaffDTO(Staff staff) {
        this.id = staff.getId();
        this.fullName = staff.getFullName();
        this.gender = staff.getGender();
        this.birthday = TimeUtil.toString(staff.getBirthday());
        this.photo = staff.getPhoto();
        this.email = staff.getEmail();
        this.phone = staff.getPhone();
        this.salary = staff.getSalary();
        this.notes = staff.getNotes();
        this.departId = staff.getDepart().getId();
        this.departName = staff.getDepart().getName();
    }

    public StaffDTO(Integer departId, String fullName) {
        this.departId = departId;
        this.page = page;
    }

    public StaffDTO(Integer departId, String fullName, Integer page, Integer maxResults) {
        this.departId = departId;
        this.page = page;
        this.maxResults = maxResults;
        this.fullName = fullName;
    }

    public List<StaffDTO> getList(List<Staff> listStaff){
        List<StaffDTO> list = new ArrayList<>();
        for (Staff staff: listStaff) {
            list.add(new StaffDTO(staff));
        };
        return list;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
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

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "StaffDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", notes='" + notes + '\'' +
                ", departId=" + departId +
                ", departName='" + departName + '\'' +
                ", page=" + page +
                ", maxResults=" + maxResults +
                ", totalPages='" + totalPages + '\'' +
                '}';
    }
}
