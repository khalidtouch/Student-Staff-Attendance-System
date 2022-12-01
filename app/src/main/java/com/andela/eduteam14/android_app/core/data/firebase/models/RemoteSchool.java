package com.andela.eduteam14.android_app.core.data.firebase.models;

import com.andela.eduteam14.android_app.core.data.models.LocalSchool;

public class RemoteSchool {

    private String SchoolCode, OrganizationId, SchoolName, AdminEmail, Address, SchoolLocation, DateModified;

    public RemoteSchool() {

    }

    public RemoteSchool(String schoolCode, String organizationId, String schoolName,
                        String adminName, String address, String schoolLocation,
                        String dateModified) {
        SchoolCode = schoolCode;
        OrganizationId = organizationId;
        SchoolName = schoolName;
        AdminEmail = adminName;
        Address = address;
        SchoolLocation = schoolLocation;
        DateModified = dateModified;
    }

    public String getAdminEmail() {
        return AdminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        AdminEmail = adminEmail;
    }

    public String getDateModified() {
        return DateModified;
    }

    public void setDateModified(String dateModified) {
        DateModified = dateModified;
    }

    public String getSchoolCode() {
        return SchoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        SchoolCode = schoolCode;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }


    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getSchoolLocation() {
        return SchoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        SchoolLocation = schoolLocation;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public LocalSchool mapToLocal() {
        return new LocalSchool(
                SchoolCode,
                OrganizationId,
                SchoolName,
                AdminEmail,
                Address,
                SchoolLocation,
                DateModified
        );
    }
}
