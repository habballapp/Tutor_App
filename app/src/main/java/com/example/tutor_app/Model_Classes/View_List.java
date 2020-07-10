package com.example.tutor_app.Model_Classes;


public class View_List {

    private String Id;
    private String FullName;
    private String PreferredSubjects;
    private String PhoneNo1;
    private String Email;
    private String TutorImage;
    private String Status;

    public View_List(String id, String fullName, String preferredSubjects, String phoneNo1, String email, String tutorImage, String status) {
        Id = id;
        FullName = fullName;
        PreferredSubjects = preferredSubjects;
        PhoneNo1 = phoneNo1;
        Email = email;
        TutorImage = tutorImage;
        Status = status;
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

    public String getPreferredSubjects() {
        return PreferredSubjects;
    }

    public void setPreferredSubjects(String preferredSubjects) {
        PreferredSubjects = preferredSubjects;
    }

    public String getPhoneNo1() {
        return PhoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        PhoneNo1 = phoneNo1;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
}
