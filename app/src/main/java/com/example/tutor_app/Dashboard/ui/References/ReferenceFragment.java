package com.example.tutor_app.Dashboard.ui.References;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.Qualification.ExpandOrCollapse;
import com.example.tutor_app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReferenceFragment extends Fragment {

    private RelativeLayout btn_reference_user, mrelativevToSlide, mRelativeZaplon;
    private boolean isVisible = false;
    private ExpandOrCollapse mAnimationManager;
    String Url_Tprofile = "https://pci.edusol.co/StudentPortal/studenttutorformsubmit.php";
    private EditText name, edt_relation, edt_occupation_ref, edt_address, edt_telephone, name1, edt_relation1, edt_occupation1, edt_present_address1, edt_telephone1;


    //profile
    String edt_fullname, edt_fname, edt_mtongue, edt_occupation, edt_cnic, edt_present_address,
            edt_permanent_address, edt_dob, edt_nationality, edt_religion, edt_phone1, edt_phone2,
            edt_email, edt_age, imageBitmapBase64;
    String spinner_conveyance_txt, spinner_profession;
    //Qualification
    String qualification, subject, edt_institute, edt_passing_year, edt_grade;
    String qualification1, subject1, edt_institute1, edt_passing_year1, edt_grade1;
    String qualification2, subject2, edt_institute2, edt_passing_year2, edt_grade2;
    String qualification3, subject3, edt_institute3, edt_passing_year3, edt_grade3;
    String qualification4, subject4, edt_institute4, edt_passing_year4, edt_grade4;
    //JobExperienceFragemnt
    String edt_etitlement, edt_organization, edt_from, edt_till;


    //AreaFragment
    String edt_classes_track, edt_pref_subject, spinner_area;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reference, container, false);
        // profile teacher
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);


        name = root.findViewById(R.id.name);
        name1 = root.findViewById(R.id.name1);
        edt_relation = root.findViewById(R.id.edt_relation);
        edt_relation1 = root.findViewById(R.id.edt_relation1);
        edt_occupation_ref = root.findViewById(R.id.edt_occupation_ref);
        edt_occupation1 = root.findViewById(R.id.edt_occupation1);
        edt_occupation_ref = root.findViewById(R.id.edt_occupation_ref);
        edt_occupation1 = root.findViewById(R.id.edt_occupation1);
        edt_telephone = root.findViewById(R.id.edt_telephone);
        edt_telephone1 = root.findViewById(R.id.edt_telephone1);


        //Profile

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
        imageBitmapBase64 = sharedPreferences.getString("tutorimageBase64", String.valueOf("data:image/png;base64," + imageBitmapBase64));


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

                String rname = name.getText().toString().trim();
                String rname1 = name1.getText().toString().trim();
                String rletaion = edt_relation.getText().toString().trim();
                String relation1 = edt_relation1.getText().toString().trim();
                String roccupation = edt_occupation_ref.getText().toString().trim();
                String roccupation1 = edt_occupation1.getText().toString().trim();
                String rphone = edt_telephone.getText().toString().trim();
                String rphone1 = edt_telephone1.getText().toString().trim();


                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG);
                uploadData();

            }
        });

        return root;
    }

    private void uploadData() {


        JSONObject map = new JSONObject();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();

//        List<String> selectedSubjects = gson.fromJson(subjects, type);

//        edt_mtongue, edt_occupation, edt_cnic, edt_present_address,
//                edt_permanent_address, edt_dob, edt_nationality, edt_religion, edt_phone1, edt_phone2,
//                edt_email, edt_age, imageBitmapBase64;
//
//        JSONArray jsonArray = new JSONArray(selectedSubjects);
//
//        map.put("fullname", edt_fullname);
//        map.put("fathusname",edt_fname);
//        map.put("mothnametounge", edt_mtongue);
//        map.put("email", email);
//        map.put("gender",);
//
//        List<String> selectedClasses = gson.fromJson(classes, type);
//        JSONArray jsonArray1 = new JSONArray(selectedClasses);
//        map.put("class", jsonArray1);
//        map.put("subjects", jsonArray);
//        map.put("contactno1", phone1);
//        map.put("contactno2", phone2);
//        map.put("contactno3", phone3);
//        map.put("salaryfrom", et_amount1.getText().toString());
//        map.put("salaryto", et_amount2.getText().toString());
//        map.put("otherclass",ctype );
//        map.put("othersubjects",ctype );
//        map.put("buildingname", edt_bno.getText().toString());
//        map.put("streetnum", edt_street.getText().toString());
//        map.put("blocknum", edt_block.getText().toString());
//        map.put("area", edt_area.getText().toString());
//        map.put("city", edt_city.getText().toString());
//        map.put("country", edt_country.getText().toString());
//        map.put("gender", spinner_gender);
//        map.put("timing", spinner_timings);
//        map.put("address", String.valueOf(edt_address.getText()));




    }
}
