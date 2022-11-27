package com.andela.eduteam14.android_app.core.data.firebase.models;

import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance;

public class RemoteDailyAttendance {
    private String AttendanceId;
    private String AdminName;
    private long MaleStudentsPresent;
    private long  FemaleStudentsPresent;
    private long MaleStaffPresent;
    private long FemaleStaffPresent;
    private String DateModified;
    private String SchoolName;


    public RemoteDailyAttendance() {

    }

    public RemoteDailyAttendance(String attendanceId, String adminName, long maleStudentsPresent,
                                 long femaleStudentsPresent, long maleStaffPresent,
                                 long femaleStaffPresent, String dateModified, String schoolName) {
        AttendanceId = attendanceId;
        AdminName = adminName;
        MaleStudentsPresent = maleStudentsPresent;
        FemaleStudentsPresent = femaleStudentsPresent;
        MaleStaffPresent = maleStaffPresent;
        FemaleStaffPresent = femaleStaffPresent;
        DateModified = dateModified;
        SchoolName = schoolName;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getAttendanceId() {
        return AttendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        AttendanceId = attendanceId;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
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

    public String getDateModified() {
        return DateModified;
    }

    public void setDateModified(String dateModified) {
        DateModified = dateModified;
    }

    public LocalDailyAttendance mapToLocal() {
        return new LocalDailyAttendance(
                AttendanceId,
                AdminName,
                MaleStudentsPresent,
                FemaleStudentsPresent,
                MaleStaffPresent,
                FemaleStaffPresent,
                DateModified,
                SchoolName
        );
    }
}
