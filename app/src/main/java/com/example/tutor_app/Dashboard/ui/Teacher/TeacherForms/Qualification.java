package com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Qualification extends Fragment {

    private boolean isVisible = false;
    private RelativeLayout mRelativeZaplon,mRelativeZaplon1,mRelativeZaplon2,mRelativeZaplon3,mRelativeZaplon4,mRelativeZaplon5;
    private RelativeLayout mRelativeToSlide,mRelativeToSlide1,mRelativeToSlide2,mRelativeToSlide3,mRelativeToSlide4,mRelativeToSlide5;
    private ExpandOrCollapse mAnimationManager;
    private RelativeLayout btn_qualification_next ,back;
    private FragmentTransaction fragmentTransaction;
    JSONObject response;
    private EditText qualification ,subject ,edt_institute, edt_passing_year ,edt_grade;
    private EditText qualification1 ,subject1 ,edt_institute1, edt_passing_year1 ,edt_grade1;
    private EditText qualification2 ,subject2 ,edt_institute2, edt_passing_year2 ,edt_grade2;
    private EditText qualification3 ,subject3 ,edt_institute3, edt_passing_year3 ,edt_grade3;
    private EditText qualification4 ,subject4 ,edt_institute4, edt_passing_year4 ,edt_grade4;
    String Url = "http://pci.edusol.co/TeacherPortal/view_profile_api.php";
    private Loader loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_section2, container, false);
        back = root.findViewById(R.id.back);
        loader = new Loader(getContext());
        mAnimationManager = new ExpandOrCollapse();

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        String str_response = personal_profile1.getString("ViewProfileData", "");

        Log.i("In Qualification", str_response);

        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        if (! str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            Log.i("responsedata", String.valueOf(response));

            viewProfile();
        }


        mRelativeZaplon = root.findViewById(R.id.relativeZaplon);
        mRelativeZaplon1 = root.findViewById(R.id.relativeZaplon1);
        mRelativeZaplon2 = root.findViewById(R.id.relativeZaplon2);
        mRelativeZaplon3 = root.findViewById(R.id.relativeZaplon3);
        mRelativeZaplon4 = root.findViewById(R.id.relativeZaplon4);



        mRelativeToSlide = root.findViewById(R.id.relativevToSlide);
        mRelativeToSlide1 = root.findViewById(R.id.relativevToSlide1);
        mRelativeToSlide2 = root.findViewById(R.id.relativevToSlide2);
        mRelativeToSlide3 = root.findViewById(R.id.relativevToSlide3);
        mRelativeToSlide4 = root.findViewById(R.id.relativevToSlide4);

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

        final List<EditText> allFields =new ArrayList<EditText>();

        allFields.add(qualification);
        allFields.add(subject);
        allFields.add(edt_institute);
        allFields.add(edt_passing_year);
        allFields.add(edt_grade);


        btn_qualification_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final SharedPreferences qualification_data = getContext().getSharedPreferences("SendData",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor profileTeacher_Qualification = qualification_data.edit();
                List<EditText> ErrorFields = new ArrayList<EditText>();//empty Edit text arraylist
                for (int j = 0; j < allFields.size(); j++) {
                    if (TextUtils.isEmpty(allFields.get(j).getText())) {
                        // EditText was empty
                        //   Fields.add(allFields.get(j).getText().toString());
                        ErrorFields.add(allFields.get(j));//add empty Edittext only in this ArayList
                        for (int i = 0; i < ErrorFields.size(); i++) {
                            //Fields.add(ErrorFields.get(i).getText().toString());
                            EditText currentField = ErrorFields.get(i);
                            currentField.setError("this field required");
                            ErrorFields.set(i, currentField);
                            currentField.requestFocus();
                        }

                    }
                }

                if (ErrorFields.isEmpty()) {

                    profileTeacher_Qualification.putString("Qualification", String.valueOf(qualification.getText()));
                    profileTeacher_Qualification.putString("SubjectSpec", String.valueOf(subject.getText()));
                    profileTeacher_Qualification.putString("InstituteUniversity", String.valueOf(edt_institute.getText()));
                    profileTeacher_Qualification.putString("YearOfPassing", String.valueOf(edt_passing_year.getText()));
                    profileTeacher_Qualification.putString("gradedivision", String.valueOf(edt_grade.getText()));

                    //

//        if (!(String.valueOf(qualification1.getText()) == null) || !(String.valueOf(subject1.getText()) == null) ||
//                !(String.valueOf(edt_institute1.getText()) == null) || !(String.valueOf(edt_passing_year1.getText()) == null)
//                || !(String.valueOf(edt_grade1.getText()) == null)) {
                    profileTeacher_Qualification.putString("Qualification1", String.valueOf(qualification1.getText()));
                    profileTeacher_Qualification.putString("SubjectSpec1", String.valueOf(subject1.getText()));
                    profileTeacher_Qualification.putString("InstituteUniversity1", String.valueOf(edt_institute1.getText()));
                    profileTeacher_Qualification.putString("YearOfPassing1", String.valueOf(edt_passing_year1.getText()));
                    profileTeacher_Qualification.putString("gradedivision1", String.valueOf(edt_grade1.getText()));
//        }
//        else{
//
//            profileTeacher_Qualification.putString("Qualification1"," ");
//            profileTeacher_Qualification.putString("SubjectSpec1"," ");
//            profileTeacher_Qualification.putString("InstituteUniversity1"," ");
//            profileTeacher_Qualification.putString("YearOfPassing1"," ");
//            profileTeacher_Qualification.putString("gradedivision1"," ");
//
//
//        }
//            if (!(String.valueOf(qualification2.getText()) == null) || !(String.valueOf(subject2.getText()) == null) ||
//                    !(String.valueOf(edt_institute2.getText()) == null) || !(String.valueOf(edt_passing_year2.getText()) == null)
//                    || !(String.valueOf(edt_grade2.getText()) == null) )
//            {
                    //
                    profileTeacher_Qualification.putString("Qualification2", String.valueOf(qualification2.getText()));
                    profileTeacher_Qualification.putString("SubjectSpec2", String.valueOf(subject2.getText()));
                    profileTeacher_Qualification.putString("InstituteUniversity2", String.valueOf(edt_institute2.getText()));
                    profileTeacher_Qualification.putString("YearOfPassing2", String.valueOf(edt_passing_year2.getText()));
                    profileTeacher_Qualification.putString("gradedivision2", String.valueOf(edt_grade2.getText()));
//            }
//            else
//            {
//                profileTeacher_Qualification.putString("Qualification2"," ");
//                profileTeacher_Qualification.putString("SubjectSpec2"," ");
//                profileTeacher_Qualification.putString("InstituteUniversity2"," ");
//                profileTeacher_Qualification.putString("YearOfPassing2"," ");
//                profileTeacher_Qualification.putString("gradedivision2"," ");
//            }
//
//
//        if (!(String.valueOf(qualification3.getText()) == null) || !(String.valueOf(subject3.getText()) == null) ||
//                !(String.valueOf(edt_institute3.getText()) == null) || !(String.valueOf(edt_passing_year3.getText()) == null)
//                || !(String.valueOf(edt_grade3.getText()) == null) )
//        {
                    //
                    profileTeacher_Qualification.putString("Qualification3", String.valueOf(qualification3.getText()));
                    profileTeacher_Qualification.putString("SubjectSpec3", String.valueOf(subject3.getText()));
                    profileTeacher_Qualification.putString("InstituteUniversity3", String.valueOf(edt_institute3.getText()));
                    profileTeacher_Qualification.putString("YearOfPassing3", String.valueOf(edt_passing_year3.getText()));
                    profileTeacher_Qualification.putString("gradedivision3", String.valueOf(edt_grade3.getText()));
//        }
//        else{
//
//            profileTeacher_Qualification.putString("Qualification3"," ");
//            profileTeacher_Qualification.putString("SubjectSpec3"," ");
//            profileTeacher_Qualification.putString("InstituteUniversity3"," ");
//            profileTeacher_Qualification.putString("YearOfPassing3"," ");
//            profileTeacher_Qualification.putString("gradedivision3"," ");
//
//
//        }
//        if (!(String.valueOf(qualification4.getText()) == null) || !(String.valueOf(subject4.getText()) == null) ||
//                !(String.valueOf(edt_institute4.getText()) == null) || !(String.valueOf(edt_passing_year4.getText()) == null)
//                || !(String.valueOf(edt_grade4.getText()) == null)
//        ) {

                    profileTeacher_Qualification.putString("Qualification4", String.valueOf(qualification4.getText()));
                    profileTeacher_Qualification.putString("SubjectSpec4", String.valueOf(subject4.getText()));
                    profileTeacher_Qualification.putString("InstituteUniversity4", String.valueOf(edt_institute4.getText()));
                    profileTeacher_Qualification.putString("YearOfPassing4", String.valueOf(edt_passing_year4.getText()));
                    profileTeacher_Qualification.putString("gradedivision4", String.valueOf(edt_grade4.getText()));
//
//        }
//        else {
//
//            profileTeacher_Qualification.putString("Qualification4", " ");
//            profileTeacher_Qualification.putString("SubjectSpec4"," ");
//            profileTeacher_Qualification.putString("InstituteUniversity4"," ");
//            profileTeacher_Qualification.putString("YearOfPassing4"," ");
//            profileTeacher_Qualification.putString("gradedivision4"," ");
//
//        }


                    profileTeacher_Qualification.apply();

                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new JobExperienceFragment());
                    fragmentTransaction.commit();
                }
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




        return root;
    }

    private void viewProfile() {
        back.setVisibility(View.GONE);
        loader.showLoader();

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
                    JSONArray qualification_teacher = response.getJSONArray("Qualification");
//                    for(int i = 0; i < qualification_teacher.length(); i++) {
//
                    if (qualification_teacher.length() > 0) {
                        mRelativeToSlide.setVisibility(View.VISIBLE);

                        Log.i("qualification_debug", qualification_teacher.getJSONObject(0).getString("Qualification"));
                        qualification.setText(qualification_teacher.getJSONObject(0).getString("Qualification"));
                        subject.setText(qualification_teacher.getJSONObject(0).getString("SubjectSpecialization"));
                        edt_institute.setText(qualification_teacher.getJSONObject(0).getString("InstituteUniversity"));
                        edt_passing_year.setText(qualification_teacher.getJSONObject(0).getString("YearOfPassing"));
                        edt_grade.setText(qualification_teacher.getJSONObject(0).getString("GradeDivision"));

                        if (qualification_teacher.length() > 1) {
                            mRelativeToSlide1.setVisibility(View.VISIBLE);

                            Log.i("qualification_debug", qualification_teacher.getJSONObject(1).getString("Qualification"));
                            qualification1.setText(qualification_teacher.getJSONObject(1).getString("Qualification"));
                            subject1.setText(qualification_teacher.getJSONObject(1).getString("SubjectSpecialization"));
                            edt_institute1.setText(qualification_teacher.getJSONObject(1).getString("InstituteUniversity"));
                            edt_passing_year1.setText(qualification_teacher.getJSONObject(1).getString("YearOfPassing"));
                            edt_grade1.setText(qualification_teacher.getJSONObject(1).getString("GradeDivision"));

                            if (qualification_teacher.length() > 2) {
                                mRelativeToSlide2.setVisibility(View.VISIBLE);

                                Log.i("qualification_debug", qualification_teacher.getJSONObject(2).getString("Qualification"));
                                qualification2.setText(qualification_teacher.getJSONObject(2).getString("Qualification"));
                                subject2.setText(qualification_teacher.getJSONObject(2).getString("SubjectSpecialization"));
                                edt_institute2.setText(qualification_teacher.getJSONObject(2).getString("InstituteUniversity"));
                                edt_passing_year2.setText(qualification_teacher.getJSONObject(2).getString("YearOfPassing"));
                                edt_grade2.setText(qualification_teacher.getJSONObject(2).getString("GradeDivision"));

                                if (qualification_teacher.length() > 3) {
                                    mRelativeToSlide3.setVisibility(View.VISIBLE);

                                    Log.i("qualification_debug", qualification_teacher.getJSONObject(3).getString("Qualification"));
                                    qualification3.setText(qualification_teacher.getJSONObject(3).getString("Qualification"));
                                    subject3.setText(qualification_teacher.getJSONObject(3).getString("SubjectSpecialization"));
                                    edt_institute3.setText(qualification_teacher.getJSONObject(3).getString("InstituteUniversity"));
                                    edt_passing_year3.setText(qualification_teacher.getJSONObject(3).getString("YearOfPassing"));
                                    edt_grade3.setText(qualification_teacher.getJSONObject(3).getString("GradeDivision"));

                                    if (qualification_teacher.length() > 4) {
                                        mRelativeToSlide4.setVisibility(View.VISIBLE);

                                        Log.i("qualification_debug", qualification_teacher.getJSONObject(4).getString("Qualification"));
                                        qualification4.setText(qualification_teacher.getJSONObject(4).getString("Qualification"));
                                        subject4.setText(qualification_teacher.getJSONObject(4).getString("SubjectSpecialization"));
                                        edt_institute4.setText(qualification_teacher.getJSONObject(4).getString("InstituteUniversity"));
                                        edt_passing_year4.setText(qualification_teacher.getJSONObject(4).getString("YearOfPassing"));
                                        edt_grade4.setText(qualification_teacher.getJSONObject(4).getString("GradeDivision"));
                                    }

                                }

                            }
                        }
                    }
//                    }
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
                    fragmentTransaction.add(R.id.container, new ProfileTeacher()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });

    }
}
