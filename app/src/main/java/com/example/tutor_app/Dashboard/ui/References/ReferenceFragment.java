package com.example.tutor_app.Dashboard.ui.References;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tutor_app.Dashboard.ui.Qualification.ExpandOrCollapse;
import com.example.tutor_app.R;


public class ReferenceFragment extends Fragment {

    private RelativeLayout btn_reference_user, mrelativevToSlide, mRelativeZaplon;
    private boolean isVisible = false;
    private ExpandOrCollapse mAnimationManager;

    //profile
    String edt_fullname, edt_fname, edt_mtongue, edt_occupation, edt_cnic, edt_present_address,
            edt_permanent_address, edt_dob, edt_nationality, edt_religion, edt_phone1, edt_phone2,
            edt_email, edt_age;
    String spinner_conveyance_txt, spinner_profession;
    //Qualification
    String qualification, subject, edt_institute, edt_passing_year, edt_grade;
    String qualification1, subject1, edt_institute1, edt_passing_year1, edt_grade1;
    String qualification2, subject2, edt_institute2, edt_passing_year2, edt_grade2;
    String qualification3, subject3, edt_institute3, edt_passing_year3, edt_grade3;
    String qualification4, subject4, edt_institute4, edt_passing_year4, edt_grade4;
    //JobExperienceFragemnt
    String  edt_etitlement,edt_organization,edt_from,edt_till;


    //AreaFragment
    String edt_classes_track,edt_pref_subject, spinner_area;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reference, container, false);
        // profile teacher
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);

        edt_fullname = sharedPreferences.getString("fullname", "");
        edt_fname = sharedPreferences.getString("fathusname", "");
        edt_mtongue = sharedPreferences.getString("mothnametounge", "");
        edt_dob = sharedPreferences.getString("dob", "");
        edt_age = sharedPreferences.getString("age", "");
        edt_nationality = sharedPreferences.getString("nationality", "");
        edt_religion = sharedPreferences.getString("religion", "");
        edt_cnic = sharedPreferences.getString("cnicno", "");
        edt_present_address = sharedPreferences.getString("presentadd", "");
        edt_permanent_address = sharedPreferences.getString("permanentadd", "");
        edt_phone1 = sharedPreferences.getString("phoneno1", "");
        edt_phone2 = sharedPreferences.getString("phoneno2", "");
        edt_email = sharedPreferences.getString("email", "");
        edt_occupation = sharedPreferences.getString("email", "");
        spinner_conveyance_txt = sharedPreferences.getString("personalconveyance", "");
        spinner_profession = sharedPreferences.getString("teacherbyprofession", "");
        // Qualification
        final SharedPreferences qualification_data = getContext().getSharedPreferences("SendData_Qualification",
                Context.MODE_PRIVATE);
        qualification = qualification_data.getString("Qualification", "");
        subject = qualification_data.getString("SubjectSpecialization", "");
        edt_institute = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year = qualification_data.getString("YearOfPassing", "");
        edt_grade = qualification_data.getString("gradedivision", "");
        //
        qualification1 = qualification_data.getString("Qualification", "");
        subject1 = qualification_data.getString("SubjectSpecialization", "");
        edt_institute1 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year1 = qualification_data.getString("YearOfPassing", "");
        edt_grade1 = qualification_data.getString("gradedivision", "");
        //
        qualification2 = qualification_data.getString("Qualification", "");
        subject2 = qualification_data.getString("SubjectSpecialization", "");
        edt_institute2 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year2 = qualification_data.getString("YearOfPassing", "");
        edt_grade2 = qualification_data.getString("gradedivision", "");
        //
        qualification3 = qualification_data.getString("Qualification", "");
        subject3 = qualification_data.getString("SubjectSpecialization", "");
        edt_institute3 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year3 = qualification_data.getString("YearOfPassing", "");
        edt_grade3 = qualification_data.getString("gradedivision", "");
        //
        qualification4 = qualification_data.getString("Qualification", "");
        subject4 = qualification_data.getString("SubjectSpecialization", "");
        edt_institute4 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year4 = qualification_data.getString("YearOfPassing", "");
        edt_grade4 = qualification_data.getString("gradedivision", "");

        //JobExperience
        final SharedPreferences job_experience = getContext().getSharedPreferences("SendData_Experience",
                Context.MODE_PRIVATE);
        edt_etitlement = job_experience.getString("JobEntitlement", "");
        edt_organization = job_experience.getString("OrganizationName", "");
        edt_from = job_experience.getString("FromTo", "");
        edt_till = job_experience.getString("Till", "");
        // areaFragment
        final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData_AreaFragment",
                Context.MODE_PRIVATE);

        edt_classes_track = area_fragmnt_data.getString("PreferredArea", "");
        edt_pref_subject = area_fragmnt_data.getString("ClassToTeach", "");
        spinner_area = area_fragmnt_data.getString("PreferredSubjects", "");





        mAnimationManager = new ExpandOrCollapse();
        mrelativevToSlide = root.findViewById(R.id.relativevToSlide);
        mRelativeZaplon = root.findViewById(R.id.relativeZaplon);

        mRelativeZaplon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isVisible) {
                    mAnimationManager.expand(mrelativevToSlide, 1000);
                    isVisible = true;
                } else if (isVisible) {
                    mAnimationManager.expand(mrelativevToSlide, 1000);
                    isVisible = false;
                }

            }
        });

        btn_reference_user = root.findViewById(R.id.btn_refernce_next);

        btn_reference_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG);
                uploadData();

            }
        });

        return root;
    }

    private void uploadData() {
    }
}
