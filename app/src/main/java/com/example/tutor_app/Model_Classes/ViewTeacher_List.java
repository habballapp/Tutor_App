package com.example.tutor_app.Model_Classes;


public class ViewTeacher_List {

    private String Id;
    private String FullName;
    private String Type;
    private String TotalExperience;
    private String Age;
    private String PresentAddress;
    private String PreferredSubjects;
    private String TutorImage;
    private String Qualification;
    private String Status;
    private String JobId;

    public ViewTeacher_List(String id, String fullName, String type, String totalExperience, String age, String presentAddress, String preferredSubjects, String tutorImage, String qualification, String status, String jobId) {
        Id = id;
        FullName = fullName;
        Type = type;
        TotalExperience = totalExperience;
        Age = age;
        PresentAddress = presentAddress;
        PreferredSubjects = preferredSubjects;
        TutorImage = tutorImage;
        Qualification = qualification;
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

    public String getTotalExperience() {
        return TotalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        TotalExperience = totalExperience;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPresentAddress() {
        return PresentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        PresentAddress = presentAddress;
    }

    public String getPreferredSubjects() {
        return PreferredSubjects;
    }

    public void setPreferredSubjects(String preferredSubjects) {
        PreferredSubjects = preferredSubjects;
    }

    public String getTutorImage() {
        return TutorImage;
    }

    public void setTutorImage(String tutorImage) {
        TutorImage = tutorImage;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
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
