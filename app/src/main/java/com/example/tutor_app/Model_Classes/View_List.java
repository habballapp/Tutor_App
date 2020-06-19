package com.example.tutor_app.Model_Classes;

import android.widget.TextView;

public class View_List {

    private String name;
    private String name_value;
    private String contact;
    private String contact_value;
    private String email;
    private String email_value;
    private String subjects;
    private String subjects_value;
    private String fees;
    private String fees_value;

    public View_List() {
    }

    public View_List(String name, String name_value, String contact, String contact_value, String email, String email_value, String subjects, String subjects_value, String fees, String fees_value) {
        this.name = name;
        this.name_value = name_value;
        this.contact = contact;
        this.contact_value = contact_value;
        this.email = email;
        this.email_value = email_value;
        this.subjects = subjects;
        this.subjects_value = subjects_value;
        this.fees = fees;
        this.fees_value = fees_value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_value() {
        return name_value;
    }

    public void setName_value(String name_value) {
        this.name_value = name_value;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_value() {
        return contact_value;
    }

    public void setContact_value(String contact_value) {
        this.contact_value = contact_value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_value() {
        return email_value;
    }

    public void setEmail_value(String email_value) {
        this.email_value = email_value;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getSubjects_value() {
        return subjects_value;
    }

    public void setSubjects_value(String subjects_value) {
        this.subjects_value = subjects_value;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getFees_value() {
        return fees_value;
    }

    public void setFees_value(String fees_value) {
        this.fees_value = fees_value;
    }
}
