package org.springboot.ssm.test.entity;

import java.sql.Timestamp;

/**
 * 用户基本信息
 *
 */
public class UserInformation {

    private String userId;

    private String userName;

    private String number;

    private String email;

    private String sex;

    private String organization;

    private String department;

    private String position;

    private String role;

    private String status;

    private String note;

    private String avatarUrl;

    private Timestamp createTime;

    private Timestamp updateTime;

    public UserInformation() {
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", organization='" + organization + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
