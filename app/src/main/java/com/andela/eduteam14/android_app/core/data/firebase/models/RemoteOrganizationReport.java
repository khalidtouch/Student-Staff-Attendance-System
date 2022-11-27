package com.andela.eduteam14.android_app.core.data.firebase.models;

public class RemoteOrganizationReport {
    private String SchoolId;

    private String SchoolName;
    private String DateModified;
    private long MaleStudentsPresent;
    private long FemaleStudentsPresent;
    private long MaleStaffPresent;
    private long FemaleStaffPresent;
    private long MaleStudentsTotal;
    private long FemaleStudentsTotal;
    private long MaleStaffTotal;
    private long FemaleStaffTotal;


    public RemoteOrganizationReport() {

    }

    public RemoteOrganizationReport(String schoolId, String schoolName, String dateModified,
                                    long maleStudentsPresent, long femaleStudentsPresent,
                                    long maleStaffPresent, long femaleStaffPresent, long maleStudentsTotal,
                                    long femaleStudentsTotal, long maleStaffTotal, long femaleStaffTotal) {
        SchoolId = schoolId;
        SchoolName = schoolName;
        DateModified = dateModified;
        MaleStudentsPresent = maleStudentsPresent;
        FemaleStudentsPresent = femaleStudentsPresent;
        MaleStaffPresent = maleStaffPresent;
        FemaleStaffPresent = femaleStaffPresent;
        MaleStudentsTotal = maleStudentsTotal;
        FemaleStudentsTotal = femaleStudentsTotal;
        MaleStaffTotal = maleStaffTotal;
        FemaleStaffTotal = femaleStaffTotal;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(String schoolId) {
        SchoolId = schoolId;
    }

    public String getDateModified() {
        return DateModified;
    }

    public void setDateModified(String dateModified) {
        DateModified = dateModified;
    }

    public long getMaleStudentsPresent() {
        return MaleStudentsPresent;
    }

    public void setMaleStudentsPresent(long maleStudentsPresent) {
        MaleStudentsPresent = maleStudentsPresent;
    }

    public long getFemaleStudentsPresent() {
        return FemaleStudentsPresent;
    }

    public void setFemaleStudentsPresent(long femaleStudentsPresent) {
        FemaleStudentsPresent = femaleStudentsPresent;
    }

    public long getMaleStaffPresent() {
        return MaleStaffPresent;
    }

    public void setMaleStaffPresent(long maleStaffPresent) {
        MaleStaffPresent = maleStaffPresent;
    }

    public long getFemaleStaffPresent() {
        return FemaleStaffPresent;
    }

    public void setFemaleStaffPresent(long femaleStaffPresent) {
        FemaleStaffPresent = femaleStaffPresent;
    }

    public long getMaleStudentsTotal() {
        return MaleStudentsTotal;
    }

    public void setMaleStudentsTotal(long maleStudentsTotal) {
        MaleStudentsTotal = maleStudentsTotal;
    }

    public long getFemaleStudentsTotal() {
        return FemaleStudentsTotal;
    }

    public void setFemaleStudentsTotal(long femaleStudentsTotal) {
        FemaleStudentsTotal = femaleStudentsTotal;
    }

    public long getMaleStaffTotal() {
        return MaleStaffTotal;
    }

    public void setMaleStaffTotal(long maleStaffTotal) {
        MaleStaffTotal = maleStaffTotal;
    }

    public long getFemaleStaffTotal() {
        return FemaleStaffTotal;
    }

    public void setFemaleStaffTotal(long femaleStaffTotal) {
        FemaleStaffTotal = femaleStaffTotal;
    }
}
