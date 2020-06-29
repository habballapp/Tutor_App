package com.example.tutor_app.Dashboard.ui.Qualification;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tutor_app.Dashboard.ui.AreaofInterest.AreaFragment;
import com.example.tutor_app.Dashboard.ui.JobExperience.JobExperienceFragment;
import com.example.tutor_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Qualification extends Fragment {

    private boolean isVisible = false;
    private RelativeLayout mRelativeZaplon,mRelativeZaplon1,mRelativeZaplon2,mRelativeZaplon3,mRelativeZaplon4,mRelativeZaplon5;
    private RelativeLayout mRelativeToSlide,mRelativeToSlide1,mRelativeToSlide2,mRelativeToSlide3,mRelativeToSlide4,mRelativeToSlide5;
    private ExpandOrCollapse mAnimationManager;
    private RelativeLayout btn_qualification_next;
    private FragmentTransaction fragmentTransaction;
    private EditText qualification ,subject ,edt_institute, edt_passing_year ,edt_grade;
    private EditText qualification1 ,subject1 ,edt_institute1, edt_passing_year1 ,edt_grade1;
    private EditText qualification2 ,subject2 ,edt_institute2, edt_passing_year2 ,edt_grade2;
    private EditText qualification3 ,subject3 ,edt_institute3, edt_passing_year3 ,edt_grade3;
    private EditText qualification4 ,subject4 ,edt_institute4, edt_passing_year4 ,edt_grade4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_section2, container, false);

        mAnimationManager = new ExpandOrCollapse();


        mRelativeZaplon = (RelativeLayout) root.findViewById(R.id.relativeZaplon);
        mRelativeZaplon1 = (RelativeLayout) root.findViewById(R.id.relativeZaplon1);
        mRelativeZaplon2 = (RelativeLayout) root.findViewById(R.id.relativeZaplon2);
        mRelativeZaplon3 = (RelativeLayout) root.findViewById(R.id.relativeZaplon3);
        mRelativeZaplon4 = (RelativeLayout) root.findViewById(R.id.relativeZaplon4);


        mRelativeToSlide = (RelativeLayout) root.findViewById(R.id.relativevToSlide);
        mRelativeToSlide1 = (RelativeLayout) root.findViewById(R.id.relativevToSlide1);
        mRelativeToSlide2 = (RelativeLayout) root.findViewById(R.id.relativevToSlide2);
        mRelativeToSlide3 = (RelativeLayout) root.findViewById(R.id.relativevToSlide3);
        mRelativeToSlide4 = (RelativeLayout) root.findViewById(R.id.relativevToSlide4);

        btn_qualification_next = root.findViewById(R.id.btn_qualification_next);
        qualification = root.findViewById(R.id.qualification);
        subject = root.findViewById(R.id.subject);
        edt_institute = root.findViewById(R.id.edt_institute);
        edt_passing_year = root.findViewById(R.id.edt_passing_year);
        edt_grade = root.findViewById(R.id.edt_grade);
        //
        qualification1 = root.findViewById(R.id.qualification1);
        subject1 = root.findViewById(R.id.subject1);
        edt_institute1 = root.findViewById(R.id.edt_institute1);
        edt_passing_year1 = root.findViewById(R.id.edt_passing_year1);
        edt_grade1 = root.findViewById(R.id.edt_grade1);
        //
        qualification2 = root.findViewById(R.id.qualification2);
        subject2 = root.findViewById(R.id.subject2);
        edt_institute2 = root.findViewById(R.id.edt_institute2);
        edt_passing_year2 = root.findViewById(R.id.edt_passing_year2);
        edt_grade2 = root.findViewById(R.id.edt_grade2);
        //
        qualification3 = root.findViewById(R.id.qualification3);
        subject3 = root.findViewById(R.id.subject3);
        edt_institute3 = root.findViewById(R.id.edt_institute3);
        edt_passing_year3 = root.findViewById(R.id.edt_passing_year3);
        edt_grade3 = root.findViewById(R.id.edt_grade3);
        //
        qualification4 = root.findViewById(R.id.qualification4);
        subject4 = root.findViewById(R.id.subject4);
        edt_institute4 = root.findViewById(R.id.edt_institute4);
        edt_passing_year4 = root.findViewById(R.id.edt_passing_year4);
        edt_grade4 = root.findViewById(R.id.edt_grade4);





        btn_qualification_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new JobExperienceFragment());
                fragmentTransaction.commit();
            }
        });


        mRelativeZaplon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide, 1000);
                    isVisible = false;
                }
            }
        });

        mRelativeZaplon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide1, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide1, 1000);
                    isVisible = false;
                }
            }
        });
        mRelativeZaplon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide2, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide2, 1000);
                    isVisible = false;
                }
            }
        });

        mRelativeZaplon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide3, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide3, 1000);
                    isVisible = false;
                }
            }
        });

        mRelativeZaplon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide4, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide4, 1000);
                    isVisible = false;
                }
            }
        });
        final SharedPreferences qualification_data = getContext().getSharedPreferences("SendData_Qualification",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher_Qualification = qualification_data.edit();
        profileTeacher_Qualification.putString("Qualification",String.valueOf(qualification.getText()));
        profileTeacher_Qualification.putString("SubjectSpecialization",String.valueOf(subject.getText()));
        profileTeacher_Qualification.putString("InstituteUniversity",String.valueOf(edt_institute.getText()));
        profileTeacher_Qualification.putString("YearOfPassing",String.valueOf(edt_passing_year.getText()));
        profileTeacher_Qualification.putString("gradedivision",String.valueOf(edt_grade.getText()));
        //

        if (!(String.valueOf(qualification1.getText()) == null) || !(String.valueOf(subject1.getText()) == null) ||
                !(String.valueOf(edt_institute1.getText()) == null) || !(String.valueOf(edt_passing_year1.getText()) == null)
                || !(String.valueOf(edt_grade1.getText()) == null) || !(String.valueOf(qualification2.getText()) == null) || !(String.valueOf(subject2.getText()) == null) ||
                !(String.valueOf(edt_institute2.getText()) == null) || !(String.valueOf(edt_passing_year2.getText()) == null)
                || !(String.valueOf(edt_grade2.getText()) == null) || !(String.valueOf(qualification3.getText()) == null) || !(String.valueOf(subject3.getText()) == null) ||
                !(String.valueOf(edt_institute3.getText()) == null) || !(String.valueOf(edt_passing_year1.getText()) == null)
                || !(String.valueOf(edt_grade3.getText()) == null) || !(String.valueOf(qualification4.getText()) == null) || !(String.valueOf(subject4.getText()) == null) ||
                !(String.valueOf(edt_institute4.getText()) == null) || !(String.valueOf(edt_passing_year4.getText()) == null)
                || !(String.valueOf(edt_grade4.getText()) == null)
        )
        {
            profileTeacher_Qualification.putString("Qualification",String.valueOf(qualification1.getText()));
            profileTeacher_Qualification.putString("SubjectSpecialization",String.valueOf(subject1.getText()));
            profileTeacher_Qualification.putString("InstituteUniversity",String.valueOf(edt_institute1.getText()));
            profileTeacher_Qualification.putString("YearOfPassing",String.valueOf(edt_passing_year1.getText()));
            profileTeacher_Qualification.putString("gradedivision",String.valueOf(edt_grade1.getText()));

            //
            profileTeacher_Qualification.putString("Qualification",String.valueOf(qualification2.getText()));
            profileTeacher_Qualification.putString("SubjectSpecialization",String.valueOf(subject2.getText()));
            profileTeacher_Qualification.putString("InstituteUniversity",String.valueOf(edt_institute2.getText()));
            profileTeacher_Qualification.putString("YearOfPassing",String.valueOf(edt_passing_year2.getText()));
            profileTeacher_Qualification.putString("gradedivision",String.valueOf(edt_grade2.getText()));

            //
            profileTeacher_Qualification.putString("Qualification",String.valueOf(qualification3.getText()));
            profileTeacher_Qualification.putString("SubjectSpecialization",String.valueOf(subject3.getText()));
            profileTeacher_Qualification.putString("InstituteUniversity",String.valueOf(edt_institute3.getText()));
            profileTeacher_Qualification.putString("YearOfPassing",String.valueOf(edt_passing_year3.getText()));
            profileTeacher_Qualification.putString("gradedivision",String.valueOf(edt_grade3.getText()));

            //
            profileTeacher_Qualification.putString("Qualification",String.valueOf(qualification4.getText()));
            profileTeacher_Qualification.putString("SubjectSpecialization",String.valueOf(subject4.getText()));
            profileTeacher_Qualification.putString("InstituteUniversity",String.valueOf(edt_institute4.getText()));
            profileTeacher_Qualification.putString("YearOfPassing",String.valueOf(edt_passing_year4.getText()));
            profileTeacher_Qualification.putString("gradedivision",String.valueOf(edt_grade4.getText()));

        }
        else {

            profileTeacher_Qualification.putString("Qualification"," ");
            profileTeacher_Qualification.putString("SubjectSpecialization"," ");
            profileTeacher_Qualification.putString("InstituteUniversity"," ");
            profileTeacher_Qualification.putString("YearOfPassing"," ");
            profileTeacher_Qualification.putString("gradedivision"," ");

            //
            profileTeacher_Qualification.putString("Qualification"," ");
            profileTeacher_Qualification.putString("SubjectSpecialization"," ");
            profileTeacher_Qualification.putString("InstituteUniversity"," ");
            profileTeacher_Qualification.putString("YearOfPassing"," ");
            profileTeacher_Qualification.putString("gradedivision"," ");

            //
            profileTeacher_Qualification.putString("Qualification"," ");
            profileTeacher_Qualification.putString("SubjectSpecialization"," ");
            profileTeacher_Qualification.putString("InstituteUniversity"," ");
            profileTeacher_Qualification.putString("YearOfPassing"," ");
            profileTeacher_Qualification.putString("gradedivision"," ");

            //
            profileTeacher_Qualification.putString("Qualification"," ");
            profileTeacher_Qualification.putString("SubjectSpecialization"," ");
            profileTeacher_Qualification.putString("InstituteUniversity"," ");
            profileTeacher_Qualification.putString("YearOfPassing"," ");
            profileTeacher_Qualification.putString("gradedivision"," ");



        }





        profileTeacher_Qualification.apply();



        return root;
    }
}
