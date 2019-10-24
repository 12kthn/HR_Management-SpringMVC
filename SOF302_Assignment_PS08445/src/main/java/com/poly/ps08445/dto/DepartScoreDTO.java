package com.poly.ps08445.dto;

import com.poly.ps08445.entities.Depart;

public class DepartScoreDTO {
    Depart depart;
    Integer tongThanhTich;
    Integer tongKyLuat;

    public DepartScoreDTO() {
    }

    public DepartScoreDTO(Depart depart, Integer tongThanhTich, Integer tongKyLuat) {
        this.depart = depart;
        this.tongThanhTich = tongThanhTich;
        this.tongKyLuat = tongKyLuat;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
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
}
