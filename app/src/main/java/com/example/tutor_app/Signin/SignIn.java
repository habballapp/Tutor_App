package com.example.tutor_app.Signin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.Dashboard_Drawer_Institute;
import com.example.tutor_app.Dashboard.ui.Dashboard_Drawer_Student;
import com.example.tutor_app.Dashboard.ui.Dashboard_Drawer_Teacher;
import com.example.tutor_app.ForgetPassword.Forget_Password;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.MyJsonArrayRequest;
import com.example.tutor_app.R;
import com.example.tutor_app.UserType.User_Type;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    private RelativeLayout btn_signin;
    private TextView txt_password, txt_create,btn_signin_txt;
    private EditText edt_email, edt_password;
    private CheckBox txt_checkbox;
    ProgressBar progressBar;
    private String URL_LOGIN = "http://pci.edusol.co/Login/login_data.php";
    String Url = "http://pci.edusol.co/StudentPortal/searchtutorApi.php";
    String Url_Institute = "http://pci.edusol.co/InstitutePortal/searchjobsApi.php";
    private String userid = "";
    private List<String> childs = new ArrayList<>();
    private Map<String, String> childMap = new HashMap<>();
    private List<String> institute = new ArrayList<>();
    private Map<String, String> intituteMap = new HashMap<>();
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private String username,password;
    private Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        loader = new Loader(SignIn.this);
        btn_signin = findViewById(R.id.btn_signin_user);
        txt_password = findViewById(R.id.txt_password);
        txt_create = findViewById(R.id.txt_create);
        txt_checkbox = findViewById(R.id.txt_checkbox);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_signin_txt = findViewById(R.id.btn_signin_txt);


        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        edt_email.setText(loginPreferences.getString("username", ""));
        edt_password.setText(loginPreferences.getString("password", ""));
        progressBar = findViewById(R.id.progress_bar);




        btn_signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (txt_checkbox.isChecked()) {
                    username = edt_email.getText().toString();
                    password = edt_password.getText().toString();

                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.apply();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

                String mEmail = edt_email.getText().toString().trim();
                String mPassword = edt_password.getText().toString().trim();

                Log.i("User",mEmail);
                if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                    try {
                        Login();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    edt_email.setError("Please insert email");
                    edt_password.setError("Please insert password");
                }


            }

        });




        txt_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignIn.this, Forget_Password.class);
                startActivity(intent);
            }
        });


        txt_create.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){

        Intent intent = new Intent(SignIn.this, User_Type.class);
        startActivity(intent);
    }
    });

    }


    private void Login() throws JSONException {

        loader.showLoader();
//        progressBar.setVisibility(View.VISIBLE);
        btn_signin_txt.setText("");

        StringRequest sr = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String result) {
                loader.hideLoader();
                Log.i("loginData", String.valueOf(result));

//                progressBar.setVisibility(View.GONE);
                try {
//                    progressBar.setVisibility(View.GONE);
                    JSONObject obj = new JSONObject(result);
                    if (!obj.getString("userid").equals("null")) {
                        SharedPreferences personal_profile = getSharedPreferences("LoginData",
                                Context.MODE_PRIVATE);
                        final SharedPreferences.Editor profileStudent = personal_profile.edit();
                        profileStudent.putString("userid",obj.getString("userid"));
                        profileStudent.putString("userrole",obj.getString("userrole"));
                        profileStudent.apply();
                        userid = obj.getString("userid");
                        btn_signin_txt.setText("SIGNIN");


                        if (obj.getString("userrole").equals("Student")){
                            getProfileData();
                            getChildren();
                            Toast.makeText(SignIn.this, "Student", Toast.LENGTH_LONG).show();

                        }
                        else if(obj.getString("userrole").equals("Teacher")){


                            Toast.makeText(SignIn.this, "Teacher", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignIn.this, Dashboard_Drawer_Teacher.class);
                            startActivity(intent);

                        }
                        else if(obj.getString("userrole").equals("Institute")){
                            getInstituteData();
                            getJob();

                        }

                    } else {
                        Toast.makeText(SignIn.this, "Username or password is incorrect.", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        btn_signin_txt.setText("SIGNIN");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);


                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loader.hideLoader();
//                progressBar.setVisibility(View.GONE);
                btn_signin_txt.setText("");
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                return map;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", edt_email.getText().toString());
                map.put("password", edt_password.getText().toString());
                return map;
            }
        };
        Volley.newRequestQueue(this).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);
    }


    private void getJob() throws JSONException {
        SharedPreferences sharedPreferences1 = getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Log.i("Id",userid);
        JSONObject map = new JSONObject();
        map.put("userid",userid);

        MyJsonArrayRequest sr = new MyJsonArrayRequest(Request.Method.POST, Url_Institute, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("Institute", String.valueOf(response));
                institute = new ArrayList<>();
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = new JSONObject(response.getString(i));
                        institute.add("\t\t\t" + obj.getString("Name"));
                        intituteMap.put(obj.getString("Name"), obj.getString("Id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Gson gson = new Gson();
                SharedPreferences personal_profile = getSharedPreferences("LoginData",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor profileStudent = personal_profile.edit();
                profileStudent.putString("institute",gson.toJson(institute));
                profileStudent.putString("instituteMap",gson.toJson(intituteMap));
                profileStudent.apply();
                Intent intent = new Intent(SignIn.this, Dashboard_Drawer_Institute.class);
                startActivity(intent);

                Log.i("Institute22", String.valueOf(institute));
                Log.i("Institute32", String.valueOf(intituteMap));

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
        Volley.newRequestQueue(getApplication()).add(sr);



    }

    private void getChildren() {

        SharedPreferences sharedPreferences1 = getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Log.i("Id",userid);

        //  userid = sharedPreferences1.getString("userid", "");
        Log.i("Id",userid);
        JSONObject map = new JSONObject();
        try {
            map.put("userid",userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MyJsonArrayRequest sr = new MyJsonArrayRequest(Request.Method.POST, Url, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("Search", String.valueOf(response));
                childs = new ArrayList<>();
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = new JSONObject(response.getString(i));
                        childs.add("\t\t\t" + obj.getString("StudentName"));
                        childMap.put(obj.getString("StudentName"), obj.getString("Id"));

                        // childsMap.put(obj.getString("StudentName"), obj.getString("Id"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Child22", String.valueOf(childs));
                Gson gson = new Gson();
                SharedPreferences personal_profile = getSharedPreferences("LoginData",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor profileStudent = personal_profile.edit();
                profileStudent.putString("children",gson.toJson(childs));
                profileStudent.putString("childrenMap",gson.toJson(childMap));
                profileStudent.apply();
                Intent intent = new Intent(SignIn.this, Dashboard_Drawer_Student.class);
                startActivity(intent);
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
        Volley.newRequestQueue(getApplication()).add(sr);
    }

    private void getInstituteData() throws JSONException {

        String Url = "http://pci.edusol.co/InstitutePortal/Addinstituteregistrationformsubmit.php";
        JSONObject map = new JSONObject();
        map.put("userid", userid);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("data", String.valueOf(response));
                try {
                    String InstituteName = response.getString("InstituteName");
                    String TypeOfInstitute = response.getString("TypeOfInstitute");
                    String Email = response.getString("Email");
                    String ContactPerson = response.getString("ContactPerson");
                    String ContactNo1 = response.getString("ContactNo1");
                    String ContactNo2 = response.getString("ContactNo2");
                    String ContactNo3 = response.getString("ContactNo3");
                    String Address = response.getString("Address");
                    String StreetNum = response.getString("StreetNum");
                    String BlockNum = response.getString("BlockNum");
                    String Area = response.getString("Area");
                    String City = response.getString("City");
                    String Country = response.getString("Country");
                    String SalaryFrom = response.getString("SalaryFrom");
                    String SalaryTo = response.getString("SalaryTo");

                    SharedPreferences personal_profile = getSharedPreferences("AddProfilePreviousData",
                            Context.MODE_PRIVATE);
                    final SharedPreferences.Editor profileStudent = personal_profile.edit();
                    profileStudent.putString("InstituteName",InstituteName);
                    profileStudent.putString("ContactNo1",ContactNo1);
                    profileStudent.putString("ContactNo2",ContactNo2);
                    profileStudent.putString("ContactNo3",ContactNo3);
                    profileStudent.putString("TypeOfInstitute",TypeOfInstitute);
                    profileStudent.putString("Email",Email);
                    profileStudent.putString("ContactPerson",ContactPerson);
                    profileStudent.putString("Address",Address);
                    profileStudent.putString("BlockNum",BlockNum);
                    profileStudent.putString("StreetNum",StreetNum);
                    profileStudent.putString("SalaryFrom",BlockNum);
                    profileStudent.putString("SalaryTo",StreetNum);
                    profileStudent.putString("Area",Area);
                    profileStudent.putString("City",City);
                    profileStudent.putString("Country",Country);
                    profileStudent.putString("SalaryFrom",SalaryFrom);
                    profileStudent.putString("SalaryTo",SalaryTo);

                    profileStudent.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("AddProfile", String.valueOf(response));
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
        Volley.newRequestQueue(SignIn.this).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(SignIn.this);
        requestQueue.add(sr);






    }

    private void getProfileData() throws JSONException {

        String Url = "http://pci.edusol.co/StudentPortal/Addstudenttutorformsubmit.php";
        JSONObject map = new JSONObject();
        map.put("userid", userid);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String FatherName = response.getString("FatherName");
                    String ContactNo1 = response.getString("ContactNo1");
                    String ContactNo2 = response.getString("ContactNo2");
                    String ContactNo3 = response.getString("ContactNo3");
                    String StudentEmail = response.getString("StudentEmail");
                    String HouseNumber = response.getString("HouseNumber");
                    String BuildingName = response.getString("BuildingName");
                    String StreetNumber = response.getString("StreetNumber");
                    String BlockNumber = response.getString("BlockNumber");
                    String Area = response.getString("Area");
                    String City = response.getString("City");
                    String Country = response.getString("Country");
                    String Gender = response.getString("Gender");

                    SharedPreferences personal_profile = getSharedPreferences("AddProfilePreviousData",
                            Context.MODE_PRIVATE);
                    final SharedPreferences.Editor profileStudent = personal_profile.edit();
                    profileStudent.putString("FatherName",FatherName);
                    profileStudent.putString("ContactNo1",ContactNo1);
                    profileStudent.putString("ContactNo2",ContactNo2);
                    profileStudent.putString("ContactNo3",ContactNo3);
                    profileStudent.putString("StudentEmail",StudentEmail);
                    profileStudent.putString("HouseNumber",HouseNumber);
                    profileStudent.putString("BuildingName",BuildingName);
                    profileStudent.putString("StreetNumber",StreetNumber);
                    profileStudent.putString("BlockNumber",BlockNumber);
                    profileStudent.putString("Area",Area);
                    profileStudent.putString("City",City);
                    profileStudent.putString("Country",Country);
                    profileStudent.putString("Gender",Gender);
                    profileStudent.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("AddProfile", String.valueOf(response));
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
        Volley.newRequestQueue(SignIn.this).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(SignIn.this);
        requestQueue.add(sr);






    }
}

