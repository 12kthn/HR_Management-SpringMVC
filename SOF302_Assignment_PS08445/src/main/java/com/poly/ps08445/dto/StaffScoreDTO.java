package com.poly.ps08445.dto;

public class StaffScoreDTO {
    private String staffFullName;
    private String staffPhoto;
    private Integer departId;
    private String departName;
    private Integer tongThanhTich;
    private Integer tongKyLuat;
    private Integer tongDiem;
    private Integer page;
    private Integer maxResults;
    private Integer totalPages;

    public StaffScoreDTO() {
    }

    public StaffScoreDTO(String staffFullName, String staffPhoto, Integer departId, String departName, Integer tongThanhTich, Integer tongKyLuat, Integer tongDiem, Integer page, Integer maxResults, Integer totalPages) {
        this.staffFullName = staffFullName;
        this.staffPhoto = staffPhoto;
        this.departId = departId;
        this.departName = departName;
        this.tongThanhTich = tongThanhTich;
        this.tongKyLuat = tongKyLuat;
        this.tongDiem = tongDiem;
        this.page = page;
        this.maxResults = maxResults;
        this.totalPages = totalPages;
    }

    public StaffScoreDTO(String staffFullName, String staffPhoto, String departName, Integer tongThanhTich, Integer tongKyLuat, Integer tongDiem) {
        this.staffFullName = staffFullName;
        this.staffPhoto = staffPhoto;
        this.departName = departName;
        this.tongThanhTich = tongThanhTich;
        this.tongKyLuat = tongKyLuat;
        this.tongDiem = tongDiem;
    }

    public StaffScoreDTO(Integer departId, String staffFullName, Integer page, Integer maxResults) {
        this.staffFullName = staffFullName;
        this.departId = departId;
        this.page = page;
        this.maxResults = maxResults;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public void setStaffFullName(String staffFullName) {
        this.staffFullName = staffFullName;
    }

    public String getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(String staffPhoto) {
        this.staffPhoto = staffPhoto;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getTongThanhTich() {
        return tongThanhTich;
    }

    public void setTongThanhTich(Integer tongThanhTich) {
        this.tongThanhTich = tongThanhTich;
    }

    public Integer getTongKyLuat() {
        return tongKyLuat;
    }

    public void setTongKyLuat(Integer tongKyLuat) {
        this.tongKyLuat = tongKyLuat;
    }

    public Integer getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(Integer tongDiem) {
        this.tongDiem = tongDiem;
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

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "StaffScoreDTO{" +
                "staffFullName='" + staffFullName + '\'' +
                ", staffPhoto='" + staffPhoto + '\'' +
                ", departId=" + departId +
                ", departName='" + departName + '\'' +
                ", tongThanhTich=" + tongThanhTich +
                ", tongKyLuat=" + tongKyLuat +
                ", tongDiem=" + tongDiem +
                ", page=" + page +
                ", maxResults=" + maxResults +
                ", totalPages=" + totalPages +
                '}';
    }
}
