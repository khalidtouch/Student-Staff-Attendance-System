package com.andela.eduteam14.android_app.core.data.firebase.models;

import com.andela.eduteam14.android_app.core.data.models.LocalOrganization;

public class RemoteOrganization {
    private String OrganizationId, Name, Location, Address, DateModified, AdminEmail;

    public RemoteOrganization() {

    }

    public RemoteOrganization(String organizationId, String name, String location,
                              String address, String dateModified, String adminEmail) {
        Name = name;
        Location = location;
        Address = address;
        DateModified = dateModified;
        OrganizationId = organizationId;
        AdminEmail = adminEmail;
    }

    public String getAdminEmail() {
        return AdminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        AdminEmail = adminEmail;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getDateModified() {
        return DateModified;
    }

    public void setDateModified(String dateModified) {
        DateModified = dateModified;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public LocalOrganization mapToLocal() {
        return new LocalOrganization(
                OrganizationId,
                Name,
                Location,
                Address,
                DateModified,
                AdminEmail
        );
    }
}
