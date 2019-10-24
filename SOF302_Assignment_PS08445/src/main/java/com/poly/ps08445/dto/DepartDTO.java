package com.poly.ps08445.dto;

import com.poly.ps08445.entities.Depart;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class DepartDTO {

    private Integer id;
    @NotEmpty(message = "Mã phòng ban không được để trống")
    private String code;
    @NotEmpty(message = "Tên phòng ban không được để trống")
    private String name;

    public DepartDTO() {
    }

    public DepartDTO(Depart depart) {
        this.id = depart.getId();
        this.code = depart.getCode();
        this.name = depart.getName();
    }

    public List<DepartDTO> getList(List<Depart> listDepart){
        List<DepartDTO> list = new ArrayList<>();
        for (Depart depart: listDepart) {
            list.add(new DepartDTO(depart));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
