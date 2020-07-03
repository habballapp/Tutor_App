package com.example.tutor_app.Dashboard.ui.References;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

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
    String Url_Tprofile = "http://pci.edusol.co/TeacherPortal/tutorformsubmit.php";
    private EditText name, edt_relation, edt_occupation_ref, edt_address, edt_telephone, name1, edt_relation1, edt_occupation1, edt_present_address1, edt_telephone1;


    //profile
    String edt_fullname, edt_fname, edt_mtongue, edt_occupation, edt_cnic, edt_present_address,
            edt_permanent_address, edt_dob, edt_nationality, edt_religion, edt_phone1, edt_phone2,
            edt_email, edt_age, imageBitmapBase64,gender,conveyance,OrganizationName;
    String spinner_conveyance_txt, spinner_profession, date_of_submission,catogery;
    //Qualification
    String qualification, edt_institute, edt_passing_year, edt_grade,SubjectSpecialization,SubjectSpecialization1,
            SubjectSpecialization2,SubjectSpecialization3,SubjectSpecialization4;

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
        edt_email = sharedPreferences.getString("email", "");
        date_of_submission = sharedPreferences.getString("dateofsubmission", "");
        conveyance = sharedPreferences.getString("personalconveyance", "");
        catogery = sharedPreferences.getString("IfInstituteOther", "");

      //  edt_occupation = sharedPreferences.getString("email", "");
        spinner_conveyance_txt = sharedPreferences.getString("personalconveyance", "");
        spinner_profession = sharedPreferences.getString("teacherbyprofession", "");
        imageBitmapBase64 = sharedPreferences.getString("tutorimageBase64", String.valueOf("data:image/png;base64," + imageBitmapBase64));
        edt_etitlement = sharedPreferences.getString("jobtitle", "");
        edt_organization = sharedPreferences.getString("orgname", "");
        edt_from  = sharedPreferences.getString("fromto","");
        edt_till = sharedPreferences.getString("till","");
        edt_classes_track = sharedPreferences.getString("classtoteach", "");
        edt_pref_subject  = sharedPreferences.getString("prefsubject","");
        spinner_area = sharedPreferences.getString("prefarea","");
        gender = sharedPreferences.getString("gender","");
        SubjectSpecialization = sharedPreferences.getString("SubjectSpecialization","");
        OrganizationName = sharedPreferences.getString("OrganizationName","");



        // Qualification
        final SharedPreferences qualification_data = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        qualification = qualification_data.getString("Qualification", "");
        SubjectSpecialization = qualification_data.getString("SubjectSpecialization", "");
        edt_institute = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year = qualification_data.getString("YearOfPassing", "");
        edt_grade = qualification_data.getString("gradedivision", "");
        //
        qualification1 = qualification_data.getString("Qualification", "");
        SubjectSpecialization1 = qualification_data.getString("SubjectSpecialization1", "");
        edt_institute1 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year1 = qualification_data.getString("YearOfPassing", "");
        edt_grade1 = qualification_data.getString("gradedivision", "");
        //
        qualification2 = qualification_data.getString("Qualification", "");
        SubjectSpecialization2 = qualification_data.getString("SubjectSpecialization2", "");
        edt_institute2 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year2 = qualification_data.getString("YearOfPassing", "");
        edt_grade2 = qualification_data.getString("gradedivision", "");
        //
        qualification3 = qualification_data.getString("Qualification", "");
        SubjectSpecialization3 = qualification_data.getString("SubjectSpecialization3", "");
        edt_institute3 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year3 = qualification_data.getString("YearOfPassing", "");
        edt_grade3 = qualification_data.getString("gradedivision", "");
        //
        qualification4 = qualification_data.getString("Qualification", "");
        SubjectSpecialization4 = qualification_data.getString("SubjectSpecialization4", "");
        edt_institute4 = qualification_data.getString("InstituteUniversity", "");
        edt_passing_year4 = qualification_data.getString("YearOfPassing", "");
        edt_grade4 = qualification_data.getString("gradedivision", "");

        //JobExperience
        final SharedPreferences job_experience = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        edt_etitlement = job_experience.getString("JobEntitlement", "");
        edt_organization = job_experience.getString("OrganizationName", "");
        edt_from = job_experience.getString("FromTo", "");
        edt_till = job_experience.getString("Till", "");
        // areaFragment
        final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);

        edt_classes_track = area_fragmnt_data.getString("classtoteach", "");
        edt_pref_subject = area_fragmnt_data.getString("prefsubject", "");
        spinner_area = area_fragmnt_data.getString("prefarea", "");


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
                try {
                    uploadData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        return root;
    }

    private void uploadData() throws JSONException {


        JSONObject map = new JSONObject();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();

//        List<String> selectedSubjects = gson.fromJson(edt_pref_subject, type);
//        JSONArray jsonArray = new JSONArray(selectedSubjects);
//
        JSONArray subArray = new JSONArray();
        if (! edt_pref_subject.equals(""))
            subArray.put(edt_pref_subject);

        List<String> classTeach = gson.fromJson(edt_classes_track, type);
        JSONArray jsonArray1 = new JSONArray(classTeach);
        map.put("classtoteach", jsonArray1);

//        List<String> prefArea = gson.fromJson(spinner_area, type);
//        JSONArray jsonArray2 = new JSONArray(prefArea);
//        map.put("prefarea", jsonArray2);

        JSONArray areaArray = new JSONArray();
        if (! spinner_area.equals(""))
            areaArray.put(spinner_area);
        map.put("prefarea",spinner_area);

        JSONArray qualificationArray = new JSONArray();
        if (! qualification.equals(""))
            qualificationArray.put(qualification);
        if (! qualification1.equals(""))
            qualificationArray.put(qualification1);
        if (! qualification2.equals(""))
            qualificationArray.put(qualification2);
        if (! qualification3.equals(""))
            qualificationArray.put(qualification3);
        if (! qualification4.equals(""))
            qualificationArray.put(qualification4);

        JSONArray subspecArray = new JSONArray();
        if (! SubjectSpecialization.equals(""))
            subspecArray.put(SubjectSpecialization);
        if (! SubjectSpecialization1.equals(""))
            subspecArray.put(SubjectSpecialization1);
        if (! SubjectSpecialization2.equals(""))
            subspecArray.put(SubjectSpecialization2);
        if (! qualification3.equals(""))
            subspecArray.put(SubjectSpecialization3);
        if (! SubjectSpecialization3.equals(""))
            subspecArray.put(SubjectSpecialization4);

        JSONArray insArray = new JSONArray();
        if (! edt_institute.equals(""))
            insArray.put(edt_institute);
        if (!edt_institute1.equals(""))
            insArray.put(edt_institute1);
        if (! edt_institute2.equals(""))
            insArray.put(edt_institute2);
        if (! edt_institute3.equals(""))
            insArray.put(edt_institute3);
        if (! edt_institute4.equals(""))
            insArray.put(edt_institute4);

        JSONArray gradeArray = new JSONArray();
        if (! edt_grade.equals(""))
            gradeArray.put(edt_grade);
        if (!edt_grade1.equals(""))
            gradeArray.put(edt_grade1);
        if (! edt_grade2.equals(""))
            gradeArray.put(edt_grade2);
        if (! edt_grade3.equals(""))
            gradeArray.put(edt_grade3);
        if (! edt_grade4.equals(""))
            gradeArray.put(edt_grade4);

        JSONArray passingArray = new JSONArray();
        if (! edt_passing_year.equals(""))
            passingArray.put(edt_passing_year);
        if (!edt_passing_year1.equals(""))
            passingArray.put(edt_passing_year1);
        if (! edt_passing_year2.equals(""))
            passingArray.put(edt_passing_year2);
        if (!edt_passing_year3.equals(""))
            passingArray.put(edt_passing_year3);
        if (! edt_passing_year4.equals(""))
            passingArray.put(edt_passing_year4);

        map.put("qualification", qualificationArray);
        map.put("SubjectSpecialization",subspecArray);
        map.put("edt_grade", gradeArray);
        map.put("edt_passing_year", passingArray);
        map.put("prefsubject",edt_pref_subject);
        map.put("OrganizationName",OrganizationName);




//        map.put("Regno", "1");
//        map.put("secretcode", "2");
        map.put("age",edt_fname);
        map.put("age",edt_age);
        map.put("fullname",edt_fullname);
        map.put("gender",gender);
        map.put("email",edt_email);
        map.put("cnicno",edt_cnic);
        map.put("mothnametounge",edt_mtongue);
        map.put("dob",edt_dob);
        map.put("email",edt_email);
        map.put("fathusname",edt_fname);
        map.put("dateofsubmission","");
        map.put("nationality",edt_nationality);
        map.put("religion",edt_religion);
        map.put("presentadd",edt_present_address);
        map.put("permanentadd",edt_permanent_address);
        map.put("phoneno1",edt_phone1);
        map.put("phoneno2",edt_phone2);
        map.put("phoneno3","");
        map.put("fbid","");
        map.put("IfInstituteOther",catogery);






        map.put("JobEntitlement",edt_etitlement);
        map.put("OrganizationName",edt_organization);
        map.put("FromTo",edt_from);
        map.put("Till",edt_till);

        map.put("dateofsubmission", date_of_submission);
        map.put("personalconveyance",spinner_conveyance_txt);
        map.put("teacherbyprofession",spinner_profession);
        map.put("phoneno3","");
        map.put("ref1Name", name.getText());
        map.put("ref1Relation", edt_relation.getText());
        map.put("ref1Occupation", edt_occupation);
        map.put("ref1TelephoneNo", edt_telephone.getText());
        map.put("ref1Relation", edt_relation.getText());
        map.put("ref2Name", "");
        map.put("ref2Relation", "");
        map.put("ref2Name", "");
        map.put("ref2Occupation", "");
        map.put("ref2TelephoneNo", "");
        map.put("re2Relation", "");


        Log.i("mapAddress", String.valueOf(map));
        map.put("tutorimageBase64",imageBitmapBase64);

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url_Tprofile, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("AddProfile", String.valueOf(response));
                try {
                    if (! response.getString("userid").equals("null"))
                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


//                    if(response != null && !response.isEmpty()){

//                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

//                    }
//                    else
//                    Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Error No Response.", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }

//                @Override
//                public String getBodyContentType() {
//                    return "json";
//                }
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> map = new HashMap<>();
//                    map.put("'name'", "'"+name+"'");
//                    map.put("fathername", fathername);
//                    map.put("email", email);
//                    map.put("class", classes);
//                    map.put("subjects", subjects);
//                    map.put("contactno1", contactno1);
//                    map.put("contactno2", contactno2);
//                    map.put("contactno3", contactno3);
//                    map.put("schoolcollege", schoolcollege);
//                    map.put("housenum",edt_house_number.getText().toString());
//                    map.put("buildingname",edt_bno.getText().toString());
//                    map.put("streetnum",edt_street.getText().toString());
//                    map.put("blocknum",edt_block.getText().toString());
//                    map.put("area",edt_area.getText().toString());
//                    map.put("city",edt_city.getText().toString());
//                    map.put("country",edt_country.getText().toString());
//                    map.put("gender",spinner_gender);
//                    map.put("desiredtiming",spinner_timings);
//                    map.put("userid",userid);
//
//                    Log.i("AddressClassDebug", String.valueOf(map));
//
//                    return map;
//                }
        };
        
        Volley.newRequestQueue(getContext()).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(sr);
    }







}

