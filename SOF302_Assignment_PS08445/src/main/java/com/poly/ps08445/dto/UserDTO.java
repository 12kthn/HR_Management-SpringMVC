package com.poly.ps08445.dto;

import com.poly.ps08445.entities.User;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private Boolean saveAccount;

    @NotEmpty(message = "Vui lòng nhập mật khẩu cũ")
    private String oldPassword;
    @NotEmpty(message = "Vui lòng nhập mật khẩu mới")
    private String newPassword;
    @NotEmpty(message = "Vui lòng nhập xác nhận mật khẩu mới")
    private String confirmPassword;

    public UserDTO() {
    }

    public UserDTO(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public UserDTO(User user){
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSaveAccount() {
        return saveAccount;
    }

    public void setSaveAccount(Boolean saveAccount) {
        this.saveAccount = saveAccount;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", saveAccount=" + saveAccount +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
