package com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms.AreaFragment;
import com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms.ExpandOrCollapse;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReferenceFragment extends Fragment {

    private RelativeLayout btn_reference_user, mrelativevToSlide, mRelativeZaplon ,back;
    private boolean isVisible = false;
    private ExpandOrCollapse mAnimationManager;
    JSONObject response;
    private Loader loader;
    private TextView back_txt;
    private TextView tool_bar_heading;
    private FragmentTransaction fragmentTransaction;
    String Url_Tprofile = "http://pci.edusol.co/TeacherPortal/tutorformsubmit.php";
    String Url = "http://pci.edusol.co/TeacherPortal/view_profile_api.php";
    private EditText name, edt_relation, edt_occupation_ref, edt_telephone, edt_present_address, name1, edt_relation1, edt_occupation1, edt_present_address1, edt_telephone1;

    //profile
    String edt_fullname, edt_fname, edt_mtongue, edt_occupation, edt_cnic,
            edt_permanent_address, edt_dob, edt_nationality, edt_religion, edt_phone1, edt_phone2,
            edt_email, edt_age, imageBitmapBase64, gender, conveyance, OrganizationName, present_address,experience_year;
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
    String edt_classes_track, edt_pref_subject, area_selected;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reference, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        tool_bar_heading = toolbar.findViewById(R.id.tool_bar_heading);
        // area select
        final SharedPreferences area_selected_data = getContext().getSharedPreferences("SendData_AreaFragment",
                Context.MODE_PRIVATE);
        area_selected =area_selected_data.getString("prefarea" ,"");
        Log.i("Selected_Area" ,area_selected);
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
        edt_present_address = root.findViewById(R.id.edt_present_address);
        edt_present_address1 = root.findViewById(R.id.edt_present_address1);
        back = root.findViewById(R.id.back);
        back_txt = root.findViewById(R.id.back_txt);
        loader = new Loader(getContext());


        //Profile

        edt_fullname = sharedPreferences.getString("fullname", "");
        edt_fname = sharedPreferences.getString("fathusname", "");
        edt_mtongue = sharedPreferences.getString("mothnametounge", "");
        edt_dob = sharedPreferences.getString("dob", "");
        edt_age = sharedPreferences.getString("age", "");
        edt_nationality = sharedPreferences.getString("nationality", "");
        edt_religion = sharedPreferences.getString("religion", "");
        edt_cnic = sharedPreferences.getString("cnicno", "");
        present_address = sharedPreferences.getString("presentadd", "");
        edt_permanent_address = sharedPreferences.getString("permanentadd", "");
        edt_phone1 = sharedPreferences.getString("phoneno1", "");
        edt_phone2 = sharedPreferences.getString("phoneno2", "");
        edt_email = sharedPreferences.getString("email", "");
        edt_email = sharedPreferences.getString("email", "");
        date_of_submission = sharedPreferences.getString("dateofsubmission", "");
        conveyance = sharedPreferences.getString("personalconveyance", "");
        catogery = sharedPreferences.getString("IfInstituteOther", "");
        experience_year = sharedPreferences.getString("experienceYear", "");

      //  edt_occupation = sharedPreferences.getString("email", "");
        spinner_conveyance_txt = sharedPreferences.getString("personalconveyance", "");
        spinner_profession = sharedPreferences.getString("teacherbyprofession", "");
        imageBitmapBase64 = sharedPreferences.getString("tutorimageBase64", String.valueOf("data:image/png;base64," + imageBitmapBase64));
        edt_etitlement = sharedPreferences.getString("jobtitle", "");
        edt_organization = sharedPreferences.getString("orgname", "");
        edt_from  = sharedPreferences.getString("fromto","");
        edt_till = sharedPreferences.getString("till","");
        //  edt_classes_track = sharedPreferences.getString("classtoteach", "");
        edt_pref_subject  = sharedPreferences.getString("prefsubject","");
        area_selected = sharedPreferences.getString("prefarea","");
        gender = sharedPreferences.getString("gender","");
        SubjectSpecialization = sharedPreferences.getString("SubjectSpecialization","");
        OrganizationName = sharedPreferences.getString("OrganizationName","");

        Log.i("totalExperience" ,experience_year);

        // Qualification
        //  final SharedPreferences qualification_data = getContext().getSharedPreferences("SendData",
        //        Context.MODE_PRIVATE);

        qualification = sharedPreferences.getString("Qualification", "");
        SubjectSpecialization = sharedPreferences.getString("SubjectSpec", "");
        edt_institute = sharedPreferences.getString("InstituteUniversity", "");
        edt_passing_year = sharedPreferences.getString("YearOfPassing", "");
        edt_grade = sharedPreferences.getString("gradedivision", "");
        //
        qualification1 = sharedPreferences.getString("Qualification1", "");
        SubjectSpecialization1 = sharedPreferences.getString("SubjectSpec1", "");
        edt_institute1 = sharedPreferences.getString("InstituteUniversity1", "");
        edt_passing_year1 = sharedPreferences.getString("YearOfPassing1", "");
        edt_grade1 = sharedPreferences.getString("gradedivision1", "");


        //
        qualification2 = sharedPreferences.getString("Qualification2", "");
        SubjectSpecialization2 = sharedPreferences.getString("SubjectSpec2", "");
        edt_institute2 = sharedPreferences.getString("InstituteUniversity2", "");
        edt_passing_year2 = sharedPreferences.getString("YearOfPassing2", "");
        edt_grade2 = sharedPreferences.getString("gradedivision2", "");
        //
        qualification3 = sharedPreferences.getString("Qualification3", "");
        SubjectSpecialization3 = sharedPreferences.getString("SubjectSpec3", "");
        edt_institute3 = sharedPreferences.getString("InstituteUniversity3", "");
        edt_passing_year3 = sharedPreferences.getString("YearOfPassing3", "");
        edt_grade3 = sharedPreferences.getString("gradedivision3", "");
        //
        qualification4 = sharedPreferences.getString("Qualification4", "");
        SubjectSpecialization4 = sharedPreferences.getString("SubjectSpec4", "");
        edt_institute4 = sharedPreferences.getString("InstituteUniversity4", "");
        edt_passing_year4 = sharedPreferences.getString("YearOfPassing4", "");
        edt_grade4 = sharedPreferences.getString("gradedivision4", "");


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
        area_selected = area_selected_data.getString("prefarea", "");


        mAnimationManager = new ExpandOrCollapse();
        mrelativevToSlide = root.findViewById(R.id.relativevToSlide);
        mRelativeZaplon = root.findViewById(R.id.relativeZaplon);

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        String str_response = personal_profile1.getString("ViewProfileData", "");
        Log.i("Job Experience", str_response);

        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        if (! str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            Log.i("JobExperience", String.valueOf(response));

            viewProfile();
        }

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

        final List<EditText> allFields = new ArrayList<EditText>();

        allFields.add(name);
        allFields.add(edt_relation);
        allFields.add(edt_occupation_ref);
        allFields.add(edt_present_address);
        allFields.add(edt_telephone);


        btn_reference_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<EditText> ErrorFields = new ArrayList<EditText>();//empty Edit text arraylist
                for (int j = 0; j < allFields.size(); j++) {
                    if (TextUtils.isEmpty(allFields.get(j).getText())) {
                        // EditText was empty
                        //   Fields.add(allFields.get(j).getText().toString());
                        ErrorFields.add(allFields.get(j));//add empty Edittext only in this ArayList
                        break;
                    }
                }
                for (int i = 0; i < ErrorFields.size(); i++) {
                    //Fields.add(ErrorFields.get(i).getText().toString());
                    EditText currentField = ErrorFields.get(i);
                    currentField.setError("this field required");
                    ErrorFields.set(i, currentField);
                    currentField.requestFocus();

                }

                if (ErrorFields.isEmpty()) {

                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG);
                    try {
                        uploadData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        });

        return root;
    }

    private void viewProfile() {

        loader.showLoader();
        back.setVisibility(View.VISIBLE);
        back_txt.setText("Back");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tool_bar_heading.setText("Dashboard");
                fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.container, new HomeFragment()).addToBackStack("null");
                fragmentTransaction.commit();
            }
        });
        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("ViewData",
                Context.MODE_PRIVATE);
        String userid = sharedPreferences1.getString("UserId", "");
        JSONObject map = new JSONObject();
        try {
            map.put("TutorId", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile", String.valueOf(response));
                loader.hideLoader();
                try {
                    JSONArray references = response.getJSONArray("References");
                    Log.i("References", String.valueOf(references));
                    btn_reference_user.setVisibility(View.GONE);

                    if (references.length() > 0) {

                        name.setText(references.getJSONObject(0).getString("Name"));
                        edt_relation.setText(references.getJSONObject(0).getString("Relation"));
                        edt_occupation_ref.setText(references.getJSONObject(0).getString("Occupation"));
                        edt_present_address.setText(references.getJSONObject(0).getString("Address"));
                        edt_telephone.setText(references.getJSONObject(0).getString("TelephoneNo"));


                        if (references.length() > 1) {

                            mrelativevToSlide.setVisibility(View.VISIBLE);
                            name1.setText(references.getJSONObject(0).getString("Name"));
                            edt_relation1.setText(references.getJSONObject(0).getString("Relation"));
                            edt_occupation1.setText(references.getJSONObject(0).getString("Occupation"));
                            edt_present_address1.setText(references.getJSONObject(0).getString("Address"));
                            edt_telephone1.setText(references.getJSONObject(0).getString("TelephoneNo"));



                        }
                    }

                        } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }
        };
        Volley.newRequestQueue(getContext()).add(sr);


    }



    private void uploadData() throws JSONException {

        loader.showLoader();
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

//        List<String> prefArea = gson.fromJson(are, type);
//        JSONArray jsonArray2 = new JSONArray(prefArea);
//        map.put("prefarea", jsonArray2);

        JSONArray areaArray = new JSONArray();
        if (! area_selected.equals(""))
            areaArray.put(area_selected);
        map.put("prefarea",area_selected);

        JSONArray qualificationArray = new JSONArray();
        Log.i("debugQualification", qualification);
        Log.i("debugQualification", qualification1);
        Log.i("debugQualification", qualification2);
        Log.i("debugQualification", qualification3);
        Log.i("debugQualification", qualification4);
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


        Log.i("Qualification", String.valueOf(qualificationArray));
        Log.i("Subjects", String.valueOf(subspecArray));
        Log.i("Grade", String.valueOf(gradeArray));
        Log.i("Passingyear", String.valueOf(passingArray));


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
        map.put("presentadd", present_address);
        map.put("permanentadd",edt_permanent_address);
        map.put("phoneno1",edt_phone1);
        map.put("phoneno2",edt_phone2);
        map.put("phoneno3","");
        map.put("fbid","");
        map.put("IfInstituteOther",catogery);
        map.put("classtoteach", edt_classes_track);
        map.put("TotalExperience", experience_year);


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
        map.put("ref1Occupation", edt_occupation_ref.getText());
        map.put("ref1TelephoneNo", edt_telephone.getText());
        map.put("ref1Address", edt_present_address.getText());
        map.put("ref2Address", edt_present_address1.getText());
        map.put("ref1Relation", edt_relation.getText());
        map.put("ref2Name", name1.getText());
        map.put("ref2Relation", edt_relation1.getText());
        map.put("ref2Occupation", edt_occupation1.getText());
        map.put("ref2TelephoneNo", edt_telephone1.getText());



        Log.i("mapAddress", String.valueOf(map));
        map.put("tutorimageBase64",imageBitmapBase64);

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url_Tprofile, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("AddProfile", String.valueOf(response));
                loader.hideLoader();
                try {
                    if (!response.getString("userid").equals("null"))
                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error ."+e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Error No Response."+error, Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }


        };
        
        Volley.newRequestQueue(getContext()).add(sr);
        // RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // requestQueue.add(sr);
    }



    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.container, new AreaFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });

    }
}

