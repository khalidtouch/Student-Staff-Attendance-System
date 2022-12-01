package com.andela.eduteam14.android_app.core.data.firebase.models;

import com.andela.eduteam14.android_app.core.data.models.LocalAdmin;

public class RemoteAdmin {

    private String AdminId;
    private String AdminName;
    private String AdminPhone;

    private String Account;

    public RemoteAdmin() {
    }

    public RemoteAdmin(String adminId, String adminName, String adminPhone, String account) {
        AdminId = adminId;
        AdminName = adminName;
        AdminPhone = adminPhone;
        Account = account;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminPhone() {
        return AdminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        AdminPhone = adminPhone;
    }

    public LocalAdmin mapToLocal() {
        return new LocalAdmin(
                AdminId,
                AdminName,
                AdminPhone,
                Account
        );
    }
}
