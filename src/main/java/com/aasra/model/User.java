package com.aasra.model;

import java.sql.Timestamp;

public class User {
    
    public enum Role {
        Customer, RestaurantOwner, DeliveryBoy, SystemAdmin
    }
    
    private int userId;
    private String name;
    private String email;
    private long phoneNo;
    private String address;
    private String userName;
    private String password;
    private Role role;
    private Timestamp createDate;
    private Timestamp lastLogin;
    private String verificationStatus;
    
    // Phone/Email Change Fields
    private String phoneChangeOtp;
    private Timestamp phoneChangeOtpExpiry;
    private String emailChangeToken;
    private Timestamp emailChangeTokenExpiry;
    private String pendingEmail;
    private Long pendingPhone;
    private String pendingUsername;
    
    // Constructors
    public User() {
        this.role = Role.Customer; // Default role
    }
    
    public User(String name, String email, long phoneNo, String address, 
                String userName, String password) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.role = Role.Customer;
    }
    
    // Getters and Setters
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public long getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
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
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public Timestamp getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    public Timestamp getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public String getVerificationStatus() {
        return verificationStatus;
    }
    
    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getPhoneChangeOtp() {
        return phoneChangeOtp;
    }

    public void setPhoneChangeOtp(String phoneChangeOtp) {
        this.phoneChangeOtp = phoneChangeOtp;
    }

    public Timestamp getPhoneChangeOtpExpiry() {
        return phoneChangeOtpExpiry;
    }

    public void setPhoneChangeOtpExpiry(Timestamp phoneChangeOtpExpiry) {
        this.phoneChangeOtpExpiry = phoneChangeOtpExpiry;
    }

    public String getEmailChangeToken() {
        return emailChangeToken;
    }

    public void setEmailChangeToken(String emailChangeToken) {
        this.emailChangeToken = emailChangeToken;
    }

    public Timestamp getEmailChangeTokenExpiry() {
        return emailChangeTokenExpiry;
    }

    public void setEmailChangeTokenExpiry(Timestamp emailChangeTokenExpiry) {
        this.emailChangeTokenExpiry = emailChangeTokenExpiry;
    }

    public String getPendingEmail() {
        return pendingEmail;
    }

    public void setPendingEmail(String pendingEmail) {
        this.pendingEmail = pendingEmail;
    }

    public Long getPendingPhone() {
        return pendingPhone;
    }

    public void setPendingPhone(Long pendingPhone) {
        this.pendingPhone = pendingPhone;
    }

    public String getPendingUsername() {
        return pendingUsername;
    }

    public void setPendingUsername(String pendingUsername) {
        this.pendingUsername = pendingUsername;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + 
               ", userName=" + userName + ", role=" + role + "]";
    }
}
