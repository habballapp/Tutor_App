package com.example.tutor_app.Dashboard.ui.Profile.Student;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfileStudent extends Fragment {
    private RelativeLayout btn_class_next;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_email,edt_fullname,edt_phone1,edt_phone2,edt_phone3,edt_fname;
    String userid, viewProfile_userid;
    String Url = "http://pci.edusol.co/StudentPortal/view_profile_api.php";
    private Loader loader;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_profile_student, container, false);

        btn_class_next = root.findViewById(R.id.btn_class_next);
        edt_email = root.findViewById(R.id.edt_email);
        edt_fullname = root.findViewById(R.id.edt_fullname);
        edt_fname = root.findViewById(R.id.edt_fname);
        edt_phone1 = root.findViewById(R.id.edt_phone1);
        edt_phone2 = root.findViewById(R.id.edt_phone2);
        edt_phone3 = root.findViewById(R.id.edt_phone3);
        loader = new Loader(getContext());

       // final EditText[] allFields = { edt_email,edt_fullname,edt_phone1,edt_phone2,edt_phone3,edt_fname};
        final List<EditText> allFields =new ArrayList<EditText>();
        allFields.add(edt_email);
        allFields.add( edt_fullname);
        allFields.add(edt_phone1);
        allFields.add( edt_phone2);
        allFields.add(edt_phone3);
        allFields.add(edt_fname);


        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        viewProfile_userid = sharedPreferences2.getString("UserId", "");
        Log.i("UserId", userid);

        if (!viewProfile_userid.equals("")) {
            viewProfile();
        } else if (! userid.equals("")) {
            try {
                getProfileData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                        Context.MODE_PRIVATE);

                List<EditText> ErrorFields =new ArrayList<EditText>();//empty Edit text arraylist
                for(int j = 0; j < allFields.size(); j++){
                    if(TextUtils.isEmpty(allFields.get(j).getText())){
                        // EditText was empty
                     //   Fields.add(allFields.get(j).getText().toString());
                        ErrorFields.add(allFields.get(j));//add empty Edittext only in this ArayList
                        for(int i = 0; i < ErrorFields.size(); i++)
                        {
                            //Fields.add(ErrorFields.get(i).getText().toString());
                            EditText currentField = ErrorFields.get(i);
                            currentField.setError("this field required");
                            ErrorFields.set(i,currentField);
                            currentField.requestFocus();
                        }

                    }
                }

                Log.i("allFields", String.valueOf(allFields));
                if(ErrorFields.isEmpty()){
                    SharedPreferences.Editor profileStudent = personal_profile.edit();
                    profileStudent.putString("name",String.valueOf(edt_fullname.getText()));
                    profileStudent.putString("fathername",String.valueOf(edt_fname.getText()));
                    profileStudent.putString("email",String.valueOf(edt_email.getText()));
                    profileStudent.putString("contactno1",String.valueOf(edt_phone1.getText()));
                    profileStudent.putString("contactno2",String.valueOf(edt_phone2.getText()));
                    profileStudent.putString("contactno3",String.valueOf(edt_phone3.getText()));
                    Log.i("Name", String.valueOf(edt_fullname));
                    profileStudent.apply();
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new SelectClass()).addToBackStack("tag");
                    fragmentTransaction.commit();
                    Toast.makeText(getContext()," All Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                }

            }


        });

        return root;
    }

    private void viewProfile() {

        loader.showLoader();
        JSONObject map = new JSONObject();
        try {
            map.put("StudentId", viewProfile_userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile", String.valueOf(response));
                Gson gson = new Gson();
                loader.hideLoader();
//                Type type = new TypeToken<JSONObject>(){}.getType();

                SharedPreferences personal_profile = getContext().getSharedPreferences("ViewProfile",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor profileStudent = personal_profile.edit();
                profileStudent.putString("ViewProfileData", gson.toJson(response));
                profileStudent.apply();

                edt_fullname.setEnabled(false);
                edt_fullname.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_fname.setEnabled(false);
                edt_fname.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_phone1.setEnabled(false);
                edt_phone1.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_phone2.setEnabled(false);
                edt_phone2.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_phone3.setEnabled(false);
                edt_phone3.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_email.setEnabled(false);
                edt_email.setTextColor(getResources().getColor(R.color.text_color_selection));
//
                try {
                    edt_fullname.setText(response.getString("StudentName"));
                    edt_fname.setText(response.getString("FatherName"));
                    edt_phone1.setText(response.getString("ContactNo1"));
                    edt_phone2.setText(response.getString("ContactNo2"));
                    edt_phone3.setText(response.getString("ContactNo3"));
                    edt_email.setText(response.getString("StudentEmail"));
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

    private void getProfileData() throws JSONException {

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("AddProfilePreviousData",
                Context.MODE_PRIVATE);



        edt_fname.setText(sharedPreferences2.getString("FatherName",""));
        edt_phone1.setText(sharedPreferences2.getString("ContactNo1",""));
        edt_phone2.setText(sharedPreferences2.getString("ContactNo2",""));
        edt_phone3.setText(sharedPreferences2.getString("ContactNo3",""));
        edt_email.setText(sharedPreferences2.getString("StudentEmail",""));
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
                    Toast.makeText(getContext(), "backStack", Toast.LENGTH_SHORT).show();
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.container, new HomeFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }


                return false;
            }
        });

    }
}
