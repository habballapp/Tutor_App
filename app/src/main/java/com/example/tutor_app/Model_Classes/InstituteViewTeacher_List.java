package com.example.tutor_app.Model_Classes;


public class InstituteViewTeacher_List {

    private String Id;
    private String FullName;
    private String Type;
    private String Age;
    private String PreferredSubjects;
    private String PresentAddress;
    private String TotalExperience;
    private String Qualification;
    private String TutorImage;
    private String Status;
    private String JobId;

    public InstituteViewTeacher_List() {
    }

    public InstituteViewTeacher_List(String id, String fullName, String type, String age, String preferredSubjects, String presentAddress, String totalExperience, String qualification, String tutorImage, String status, String jobId) {
        Id = id;
        FullName = fullName;
        Type = type;
        Age = age;
        PreferredSubjects = preferredSubjects;
        PresentAddress = presentAddress;
        TotalExperience = totalExperience;
        Qualification = qualification;
        TutorImage = tutorImage;
        Status = status;
        JobId = jobId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPreferredSubjects() {
        return PreferredSubjects;
    }

    public void setPreferredSubjects(String preferredSubjects) {
        PreferredSubjects = preferredSubjects;
    }

    public String getPresentAddress() {
        return PresentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        PresentAddress = presentAddress;
    }

    public String getTotalExperience() {
        return TotalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        TotalExperience = totalExperience;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getTutorImage() {
        return TutorImage;
    }

    public void setTutorImage(String tutorImage) {
        TutorImage = tutorImage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }
}
